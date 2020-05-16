package server;

import entity.User;
import service.ServiceLiked;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LikedServlet extends HttpServlet {
    private final ServiceLiked serviceLiked = new ServiceLiked();
    private List<User> likedUsers;
    private final TemplateEngine engine;


    public LikedServlet(TemplateEngine engine) throws IOException {
        this.engine = engine;
        likedUsers = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collected = new BufferedReader(new FileReader(new File("templates/people-list.ftl"))).lines().collect(Collectors.joining("\n"));
        HashMap<String, Object> data = new HashMap<>();
        likedUsers = serviceLiked.getAll();
        data.put("users", likedUsers);
        engine.render("people-list.ftl", data, resp);
    }

}
