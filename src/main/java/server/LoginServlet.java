package server;

import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collected = new BufferedReader(new FileReader(new File("templates/login.html"))).lines().collect(Collectors.joining("\n"));
        try(PrintWriter w = resp.getWriter()){
            w.write(collected);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");




        // IF EVERYTHING IS OK
        Cookie cookie = new Cookie(name, password);
        resp.addCookie(cookie);
    }
}
