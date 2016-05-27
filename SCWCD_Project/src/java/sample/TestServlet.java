/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Rakesh
 */
public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        getServletContext().getRequestDispatcher("Test.jsp");
        PrintWriter w = response.getWriter();
        w.print("");
        ServletOutputStream s = response.getOutputStream();
        Object o = request.getParameter("");
        Float f = Float.parseFloat(request.getParameter(""));
        byte[] b = new byte[102];
        s.write(b);  
        
    } 
}
