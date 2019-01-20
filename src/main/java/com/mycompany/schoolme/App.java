package com.mycompany.schoolme;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mycompany.schoolme.application.SchoolMeApp;
import com.mycompany.schoolme.cache.Cache;

/**
 * Implements a REST api end point that serves student information to clients. Swagger documentation
 * is passed back when calling the /api/v1/swagger.json end point.
 */
public class App {

  private static final Logger logger = LoggerFactory.getLogger(SchoolMeApp.class);

  /**
   * Main method that is called to bootstrap the application.
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(createApp()));
    jerseyServlet.setInitOrder(1);

    Server jettyServer = new Server(8080);
    ServletContextHandler contextHandler = new ServletContextHandler(jettyServer, "/*");
    contextHandler.addServlet(jerseyServlet, "/api/v1/*");

//    URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
    // ResourceConfig config = new ResourceConfig(MyResource.class);
    // Server jettyServer = JettyHttpContainerFactory.createServer(baseUri, createApp());


    logger.info("School Me Example App");

    try {
      jettyServer.start();
      logger.info(String.format("Application started.%nStop the application using CTRL+C"));
      jettyServer.join();
    } catch (InterruptedException e) {
      logger.error("InterupptedException: " + e.getMessage());
      logger.error("Stack trace: " + e.getStackTrace());
    } catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      logger.error("Stack trace: " + e.getStackTrace());
    } finally {
      jettyServer.destroy();
    }
  }

  public static ResourceConfig createApp() {
    return new SchoolMeApp();
  }
}
