/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Rakesh
 */
public class Classic1 extends TagSupport {
    
    private JspWriter out;

    public int doStartTag() throws JspException {
        try {
            out = pageContext.getOut();
            out.println("Hello World, my first Classic Tag");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspException {
        try {
            out = pageContext.getOut();
            out.println("Inside doEndTag()");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
}
