package com.rak.swing;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileSystemTree extends JFrame implements PropertyChangeListener {

	private JTree tree = null;

	private DefaultMutableTreeNode root;

	private static final long serialVersionUID = -8597066704826959835L;

	public FileSystemTree() {
		constructGUI();
	}

	private void constructGUI() {
		setTitle("File System");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		File file = new File("D:\\");
		root = new DefaultMutableTreeNode(file);
		addTreeNodes(file, null);
		panel.add(new JScrollPane(tree));
		add(panel, BorderLayout.CENTER);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println(e.getPath().toString());
			}
		});
	}

	private void addTreeNodes(File file, DefaultMutableTreeNode dirNode) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File nextFile : files) {
				if (nextFile.isDirectory()) {
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
							nextFile.getName());
					if (dirNode == null) {
						root.add(newNode);
					} else {
						dirNode.add(newNode);
					}
					addTreeNodes(nextFile, newNode);
				} else {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(
							nextFile.getName());
					if (dirNode == null) {
						root.add(node);
					} else {
						dirNode.add(node);
					}
				}
			}
			tree = new JTree(root);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FileSystemTree().setVisible(true);
			}
		});
		System.out.println("Hi");
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}

}
