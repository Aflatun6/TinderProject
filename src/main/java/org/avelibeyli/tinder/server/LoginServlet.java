package org.avelibeyli.tinder.server;

import lombok.SneakyThrows;
import org.avelibeyli.tinder.service.ServiceSigning;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    ServiceSigning service;

    public LoginServlet() throws SQLException {
        service = new ServiceSigning();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String collected = new BufferedReader(new FileReader(new File("templates/login.html"))).lines().collect(Collectors.joining("\n"));
        try (PrintWriter w = resp.getWriter()) {
            w.write(collected);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (service.exist(name, password)) {
            // IF EVERYTHING IS OK
            Cookie cookie = new Cookie(name, password);
            resp.addCookie(cookie);
            resp.sendRedirect("/users");
        } else resp.sendRedirect("/login");
    }
}
