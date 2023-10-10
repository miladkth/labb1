package filters;

import bo.handlers.SessionService;
import ui.DTOs.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WareHouseAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;

        SessionService session = new SessionService(req.getSession());
        UserDTO user = session.getUser();

        if(user != null && (user.getRole().equals("admin") || user.getRole().equals("warehouse"))){
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletResponse res = (HttpServletResponse) response;
        req.setAttribute("code", 403);
        req.setAttribute("message", "Access denied!");
        req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
    }
}