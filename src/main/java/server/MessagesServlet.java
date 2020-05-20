package server;


import service.ServiceMessaging;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final ServiceMessaging service;
    private static int whom;

    public MessagesServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        service = new ServiceMessaging();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        whom = Integer.parseInt(req.getPathInfo().split("/")[1]);
        HashMap<String, Object> data = new HashMap<>();
        engine.render("templates/chat.ftl", data, resp);
    }
}
