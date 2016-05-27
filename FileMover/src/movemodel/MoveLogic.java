/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the editor. 
 */
package movemodel;

/** 
 * 
 * @author Rakesh 
 */
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MoveLogic {

    private ArrayList<File> masterCollector = new ArrayList<File>();
    private String sourceRootPath;
    private String targetRootPath;

    public boolean moveFiles(String sourceDirPath, String targetDirPath) {
        boolean fileMoved = false;
        this.sourceRootPath = sourceDirPath;
        this.targetRootPath = targetDirPath;
        try {
            FileChannel in = null;
            FileChannel out = null;
            File sourceDir = new File(sourceDirPath);
            ArrayList<File> foldersInFolder = new ArrayList<File>();
            String tgtName = null;
            if (sourceDir.isDirectory()) {
                String targetName = targetDirPath;
                File targetDir = new File(targetName);
                targetDir.mkdir();
                File[] filesInSourceDir = sourceDir.listFiles();
                for (int i = 0; i < filesInSourceDir.length; i++) {
                    File file = filesInSourceDir[i];
                    if (file.isFile()) {
                        String fileName = file.getName();
                        tgtName = targetName;
                        in = new FileInputStream(file).getChannel();
                        out = new FileOutputStream(tgtName + "\\" + fileName).getChannel();
                        long size = in.size();
                        MappedByteBuffer buf = in.map(
                                FileChannel.MapMode.READ_ONLY, 0, size);
                        out.write(buf);
                        out.close();
                        in.close();
                        masterCollector.add(file);
                    } else {
                        foldersInFolder.add(file);
                    }
                }
                if (foldersInFolder.size() > 0) {
                    moveFiles(foldersInFolder, targetName);
                }
                deleteAllFiles(masterCollector);
                fileMoved = true;
            } else {
                System.out.println("Directory name not correct");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileMoved;
    }

    private void moveFiles(ArrayList<File> folders, String targetName) {
        try {
            ArrayList<File> newFolders = new ArrayList<File>();
            FileChannel in = null;
            FileChannel out = null;
            String tgtName = null;
            for (int i = 0; i < folders.size(); i++) {
                File file = folders.get(i);
                String oldPath = file.getAbsolutePath();
                if (file.isDirectory()) {
                    int position = 0;
                    LOOP:
                    for (int x = (oldPath.length() - 1); x > 0; x--) {
                        char character = oldPath.charAt(x);
                        if (character == '\\') {
                            position = x;
                            break LOOP;
                        }
                    }
                    String newSubfolderName = oldPath.substring(position + 1);
                    tgtName = targetName + "\\" + newSubfolderName;
                    File tempfile = new File(tgtName);
                    tempfile.mkdir();
                }
                File[] files = file.listFiles();
                for (int j = 0; j < files.length; j++) {
                    File fileInFolder = files[j];
                    if (fileInFolder.isFile()) {
                        String fileName = fileInFolder.getName();
                        in = new FileInputStream(fileInFolder).getChannel();
                        out = new FileOutputStream(tgtName + "\\" + fileName).getChannel();
                        long size = in.size();
                        MappedByteBuffer buf = in.map(
                                FileChannel.MapMode.READ_ONLY, 0, size);
                        out.write(buf);
                        out.close();
                        in.close();
                        masterCollector.add(fileInFolder);
                    } else {
                        newFolders.add(fileInFolder);
                    }
                }
            }
            if (newFolders.size() > 0) {
                for (int i = 0; i < newFolders.size(); i++) {
                    ArrayList<File> folder = new ArrayList<File>();
                    File temp = newFolders.get(i);
                    String olPath = temp.getParent();
                    int position = 0;
                    LOOP:
                    for (int x = (olPath.length() - 1); x > 0; x--) {
                        char character = olPath.charAt(x);
                        if (character == '\\') {
                            position = x;
                            break LOOP;
                        }
                    }
                    String x = olPath.substring(position + 1);
                    targetName = targetName + "\\" + x;
                    if (getCurrentDir(olPath, targetName) != 0) {
                        int difference = getCurrentDir(olPath, targetName);
                        targetName = shave(targetName, olPath, difference);
                    }
                    targetName = checkSimilarity(olPath, targetName);
                    folder.add(temp);
                    moveFiles(folder, targetName);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String shave(String string, String oldPath, int distance) {
        int position = 0;
        int pos1 = 0;
        int pos2 = 0;
        LOOP:
        for (int i = (string.length() - 1); i > 0; i--) {
            char character = string.charAt(i);
            if (character == '\\') {
                distance -= 1;
                position = i;
                if (distance == 0) {
                    break LOOP;
                }
            }
        }
        for (int i = (oldPath.length() - 1); i > 0; i--) {
            char character = oldPath.charAt(i);
            if (character == '\\') {
                pos1 = i;
                break;
            }
        }
        oldPath = oldPath.substring(pos1 + 1);
        String toReturn = string.substring(0, position);
        for (int i = (toReturn.length() - 1); i > 0; i--) {
            char character = string.charAt(i);
            if (character == '\\') {
                pos2 = i;
                break;
            }
        }
        toReturn = toReturn.substring(0, pos2) + "\\" + oldPath;
        return toReturn;
    }

    private int getCurrentDir(String path1, String path2) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < path1.length(); i++) {
            char character = path1.charAt(i);
            if (character == '\\') {
                count1 += 1;
            }
        }
        for (int i = 0; i < path2.length(); i++) {
            char character = path2.charAt(i);
            if (character == '\\') {
                count2 += 1;
            }
        }
        return (count2 - count1);
    }

    private String checkSimilarity(String path1, String path2) {
        String temp1 = path1.substring(sourceRootPath.length());
        path2 = targetRootPath + temp1;
        return path2;
    }

    private void deleteAllFiles(ArrayList<File> allFiles) {
        ArrayList<File> newList = new ArrayList<File>();
        File[] files = new File[allFiles.size()];
        for (int i = 0; i < allFiles.size(); i++) {
            File file = allFiles.get(i);
            if (file.isFile()) {
                files[i] = file;
            } else {
                File[] filesToDelete = file.listFiles();
                if (filesToDelete == null || filesToDelete.length <= 1) {
                    file.delete();
                } else {
                    newList.add(file);
                }

            }
        }
        if (newList.size() > 0) {
            deleteAllFiles(newList);
        }
        deleteAllFiles(files);
    }

    private void deleteAllFiles(File[] fileToDelete) {
        System.gc();
        for (int i = 0; i < fileToDelete.length; i++) {
            File file = fileToDelete[i];
            file.delete();
        }
    }
} 

