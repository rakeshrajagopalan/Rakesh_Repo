/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movecontoller;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import movemodel.MoveLogic;

/**
 *
 * @author Rakesh
 */
public class MoveController extends HttpServlet {

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sourceDir = request.getParameter("sourcedir");
        String targetDir = request.getParameter("targetdir");
        if (sourceDir != null && targetDir != null && !sourceDir.equals("") && !targetDir.equals("") && sourceDir.substring(1, 3).equals(":\\") && targetDir.substring(1, 3).equals(":\\")) {
            MoveLogic logic = new MoveLogic();
            boolean filesMoved = logic.moveFiles(sourceDir,targetDir);
            if(filesMoved) {
                response.sendRedirect("FilesMoved.jsp");
            } else {
                response.sendRedirect("FilesNotMoved.jsp");
            }
        } else {
            response.sendRedirect("WrongPath.jsp");
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
