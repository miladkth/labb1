package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JspFileAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        String uri = req.getRequestURI();

        if(uri.equals("/")){
            filterChain.doFilter(request, response);
        }else{
            req.setAttribute("code", 400);
            req.setAttribute("message", "Access to all jsp file has been denied");
            req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, response);
        }
    }
}