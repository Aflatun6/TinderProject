package org.avelibeyli.tinder.server;

import org.avelibeyli.tinder.entity.CurrentState;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for(Cookie c: cookies){
            if(c.getName().equals(CurrentState.getCurrentUser().getName())){
                c.setMaxAge(0);
                resp.addCookie(c);
//                CurrentState.setCurrentUser(null);
            }
        }
        resp.sendRedirect("/login");
    }
}
