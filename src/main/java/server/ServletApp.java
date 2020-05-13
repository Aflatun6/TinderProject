package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./templates");
        handler.addServlet(new ServletHolder(new UsersServlet(engine)),"/users/*");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)),"/liked/*");
        handler.addServlet(new ServletHolder(new StaticServlet()),"/static/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
