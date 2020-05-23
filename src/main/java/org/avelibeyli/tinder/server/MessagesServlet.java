package org.avelibeyli.tinder.server;


import org.avelibeyli.tinder.entity.Message;
import org.avelibeyli.tinder.entity.User;
import lombok.SneakyThrows;
import org.avelibeyli.tinder.service.ServiceMessaging;
import org.avelibeyli.tinder.service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final ServiceMessaging service;
    private static int whom;
    ServiceUsers serviceUsers;


    public MessagesServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        service = new ServiceMessaging();
        serviceUsers = new ServiceUsers();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getPathInfo().substring(1);
        whom = Integer.parseInt(s);
        User userwhom = serviceUsers.get(whom);
        List<Message> all = service.getAll(whom);
        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", all);
        data.put("whom", whom);
        data.put("name", userwhom.getName());
        data.put("imageurl", userwhom.getImageURL());
        engine.render("chatwithall.ftl", data, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");
        service.add(whom, content);
        resp.sendRedirect(String.valueOf(whom));
    }
}
