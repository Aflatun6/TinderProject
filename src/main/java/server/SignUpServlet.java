package server;


import lombok.SneakyThrows;
import service.ServiceSigning;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class SignUpServlet extends HttpServlet {

    ServiceSigning service ;

    public SignUpServlet() throws SQLException {
        service = new ServiceSigning();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collected = new BufferedReader(new FileReader(new File("templates/signUp.html"))).lines().collect(Collectors.joining("\n"));
        try(PrintWriter w = resp.getWriter()){
            w.write(collected);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String imageurl = req.getParameter("imageurl");
        service.add(name,imageurl,password);
        resp.sendRedirect("/login");
    }
}
