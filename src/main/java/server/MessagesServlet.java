package server;


import entity.Message;
import entity.User;
import lombok.SneakyThrows;
import service.ServiceMessaging;
import service.ServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Message> messagesFromMe = service.getMessagesFromMe(whom);
        List<Message> messagesToMe = service.getMessagesToMe(whom);
        List<Message> all = service.getAll(whom);
        HashMap<String, Object> data = new HashMap<>();
//        data.put("messagesFromMe", messagesFromMe);
//        data.put("messagesToMe", messagesToMe);
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
