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
            request.getRequestDispatcher("/home").forward(request, response);
        } else if (uri.equals("/upload.jsp")) {
            request.getRequestDispatcher("/upload.jsp").forward(request, response);
        }
        else{
            req.setAttribute("error", "Access to .jsp files denied");
            req.setAttribute("src", uri);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}