package server;

import entity.User;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class UsersServlet extends HttpServlet {
    private Service service = new Service();
    private User user;
    private List<User> unlikedUsers;
    private final TemplateEngine engine;

    public UsersServlet(TemplateEngine engine) {
        this.engine = engine;
        unlikedUsers = new ArrayList<>();
        service.add("Joanna", "data:image/jpeg;base64,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");
        service.add("Andrew", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR6CWYYBprZezOw4cPNt_l3VTnwcM3Dz95zlb9mEemFybrUSDNR&usqp=CAU");
        service.add("Elizabeth", "data:image/jpeg;base64,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");
        service.add("Neil", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeJ2JgXmP9JuCms0xCokhMSwqaubpVl-r928NezryF9uh2LEtD&usqp=CAU");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = service.findFirst();
        if (user != null) {
            HashMap<String, Object> data = new HashMap<>();
            String name = user.getName();
            String imageURL = user.getImageURL();
            data.put("name", name);
            data.put("imageURL", imageURL);
            engine.render("like-page.html", data, resp);
        } else {
            resp.sendRedirect("/liked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");
        service.delete(user);
        if (like != null) {
            service.addLikedUser(user);
        } else addAgain();
        fillUsers();
        resp.sendRedirect("/users");
    }

    private void addAgain() {
        unlikedUsers.add(user);
    }

    private void fillUsers() {
        if (service.getAll().size() == 0) {
            for (User u : unlikedUsers) {
                service.add(u.getName(), u.getImageURL());
            }
            unlikedUsers.clear();
        }
    }

}
