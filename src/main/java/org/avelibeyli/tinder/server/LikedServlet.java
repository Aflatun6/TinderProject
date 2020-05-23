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

public class LikedServlet extends HttpServlet {
    private final TemplateEngine engine;
    ServiceUsers service;

    public LikedServlet(TemplateEngine engine) throws IOException, SQLException {
        this.engine = engine;
        service = new ServiceUsers();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        List<User> likedUsers = service.getLiked();
        data.put("users", likedUsers);
        engine.render("people-list.ftl", data, resp);
    }

}
