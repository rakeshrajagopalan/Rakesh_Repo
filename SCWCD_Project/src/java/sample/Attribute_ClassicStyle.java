/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Rakesh
 */
public class Attribute_ClassicStyle extends TagSupport {

    private List<Movie> movieList;
    private JspWriter out;
    private int listSize;

    public int doStartTag() throws JspException {
        try {
            listSize = 0;
            out = pageContext.getOut();
            out.println("Inside doStartTag()");
            pageContext.setAttribute("m", movieList.get(listSize));
            listSize++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspException {
        if (listSize < movieList.size()) {
            try {
                pageContext.setAttribute("m", movieList.get(listSize));
                listSize++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    public int doEndTag() throws JspException {
        try {
            out = pageContext.getOut();
            out.println("Inside doEndTag()");
        } catch (IOException ex) {
            Logger.getLogger(Attribute_ClassicStyle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EVAL_PAGE;
    }

    public void setMovieList(List movieList) {
        this.movieList = movieList;
    }
}
