package filters;

import bo.handlers.SessionService;
import bo.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        String uri = req.getRequestURI();

        SessionService session = new SessionService(req.getSession());
        User user = session.getUser();

        if(user != null){
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("user access filter");
        System.out.println(uri);

    }
}
