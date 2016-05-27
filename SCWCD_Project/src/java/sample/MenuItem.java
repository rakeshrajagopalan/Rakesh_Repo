package sample;

import java.io.IOException;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class MenuItem extends SimpleTagSupport {
    private String item;
    public void setItem(String item) {
        this.item = item;
    }
    public String getItem() {
        return item;
    }
    public void doTag() throws IOException,JspException {
        JspWriter writer = getJspContext().getOut();
        Menu menu = (Menu) getParent();
        menu.setItem(item);
    }
}
