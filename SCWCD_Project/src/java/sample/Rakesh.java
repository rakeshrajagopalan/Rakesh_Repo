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
public class Rakesh extends SimpleTagSupport {

    private String user;

    public void doTag() throws JspException, IOException {
        getJspContext().getOut().write("Hello '" + user + "', ");
        getJspContext().getOut().write("Your advice is: " + getAdvice());
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String getAdvice() {
        String[] truth = {"Jai Sri Ram",
            "Praise the Lord", "The Lord is my Shepherd",
            "Sri Rama Jayam", "God is great"
        };
        return truth[(int) (Math.random() * truth.length)];
    }
}
