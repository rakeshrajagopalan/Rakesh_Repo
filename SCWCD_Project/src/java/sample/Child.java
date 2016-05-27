/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Rakesh
 */
public class Child extends SimpleTagSupport {

    private static int count;

    public void doTag() throws JspException, IOException {
        if (count == 0) {
            Parent parent = (Parent) getParent();
            getJspContext().getOut().println(parent.getName());
        }
        getJspBody().invoke(null);
        count ++;
    }
}
