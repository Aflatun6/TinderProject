package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletApp {
    public static void main(String[] args) throws Exception {
        DbSetup.migrate(
                "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d1h69thpfb2nha",
                "slwfzdunpgpvgz",
                "cf9a4a0e54c459192e02bd52e65896245e749fd9d939065dc1a96ecf056042fe"
        );


        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./templates");
        handler.addServlet(new ServletHolder(new UsersServlet(engine)), "/users/*");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked/*");
        handler.addServlet(new ServletHolder(new MessagesServlet(engine)), "/messages/*");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        handler.addServlet(new ServletHolder(new SignUpServlet()), "/signUp/*");
        handler.addServlet(new ServletHolder(new StaticServlet()), "/static/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
