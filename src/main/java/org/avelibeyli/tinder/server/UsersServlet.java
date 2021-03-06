package org.avelibeyli.tinder.server;

import org.avelibeyli.tinder.entity.User;
import lombok.SneakyThrows;
import org.avelibeyli.tinder.service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class UsersServlet extends HttpServlet {
    private ServiceUsers serviceUsers;
    private User user;
    private final TemplateEngine engine;

    public UsersServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        serviceUsers = new ServiceUsers();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = serviceUsers.findFirst();
        if (user != null) {
            HashMap<String, Object> data = new HashMap<>();
            String name = user.getName();
            String imageurl = user.getImageURL();
            data.put("name", name);
            data.put("imageurl", imageurl);
            engine.render("like-page.ftl", data, resp);
        } else {
            resp.sendRedirect("/liked");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");
        if (like != null) {
            serviceUsers.addLiked(user);
        } else {
            serviceUsers.addAgain(user);
        }
        resp.sendRedirect("/users");
    }
}