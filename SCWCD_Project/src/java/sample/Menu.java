package sample;

import java.util.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Menu extends TagSupport {
    
    private List menuItem;
    
    public void setItem(String item) {
        menuItem.add(item);
    }   
    
    public int doStartTag() throws JspException {
        menuItem = new ArrayList();
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspException {
        try {
            JspWriter writer = pageContext.getOut();
            writer.println("The items in the list are: ");
            for(Object o : menuItem) {
                writer.println(o);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
}
