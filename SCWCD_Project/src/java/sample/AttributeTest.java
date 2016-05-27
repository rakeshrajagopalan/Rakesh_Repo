package sample;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AttributeTest extends SimpleTagSupport {
    private List<Movie> movieList;
    public void doTag() throws JspException,IOException {
        for(Movie m: movieList) {
            getJspContext().setAttribute("m", m);
            getJspBody().invoke(null);
        }
        throw new SkipPageException();
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
