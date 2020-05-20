package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServletApp {
    public static void main(String[] args) throws Exception {
        DbSetup.migrate(
                "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha",
                "slwfzdunpgpvgz",
                "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe"
        );

        EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./templates");
        handler.addFilter(CookieFilter.class, "/users", ft);
        handler.addServlet(new ServletHolder(new UsersServlet(engine)), "/users/*");
        handler.addFilter(CookieFilter.class, "/liked", ft);
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked/*");
        handler.addFilter(CookieFilter.class, "/messages/*", ft);
        handler.addServlet(new ServletHolder(new MessagesServlet(engine)), "/messages/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new SignUpServlet()), "/signUp/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout/*");
        handler.addServlet(new ServletHolder(new StaticServlet()), "/static/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
