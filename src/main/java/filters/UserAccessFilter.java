package filters;

import bo.handlers.SessionService;
import bo.entities.User;
import ui.DTOs.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        String uri = req.getRequestURI();

        SessionService session = new SessionService(req.getSession());
        UserDTO user = session.getUser();

        if(user != null){
            filterChain.doFilter(request, response);
            return;
        }

        if(uri.equals("/user/login")){
            filterChain.doFilter(request, response);
            return;
        }
        if(uri.equals("/user/register")){
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect("/user/login");
    }
}
