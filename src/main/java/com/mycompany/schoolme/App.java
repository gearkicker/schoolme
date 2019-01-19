package com.mycompany.schoolme;

import org.eclipse.jetty.server.Server;
import com.mycompany.schoolme.cache.Cache;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import java.io.IOException;

/**
 * Implements a REST api end point that serves student information to clients. Swagger documentation
 * is passed back when calling the /api/v1/swagger.json end point.
 */
public class App {


  /**
   * Main method that is called to bootstrap the application.
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    System.out.println("School Me Example App");
    try {
      Cache.init();
    } catch (IOException e) {
      System.out.println("Can not open student data file: " + e.getMessage());
    }
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    Server jettyServer = new Server(8080);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet =
        context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/v1/*");
    jerseyServlet.setInitOrder(1);

    // Tells the Jersey Servlet which REST service/class to load.
    // Load School Me end points
    // Load Swagger end points
    jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
        "io.swagger.jaxrs.listing;com.mycompany.schoolme.resource");

    // Allow jersey to us Jackson for JSON
    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
        "org.glassfish.jersey.jackson.JacksonFeature");

    // Enable the swagger servlet
    ServletHolder swaggerServlet =
        context.addServlet(io.swagger.jersey.config.JerseyJaxrsConfig.class, "/swaggger-core");
    swaggerServlet.setInitOrder(2);
    swaggerServlet.setInitParameter("api.version", "1.0.0");
    swaggerServlet.setInitParameter("swagger.api.basepath", "http://localhost:8080/api/v1");

    try {
      jettyServer.start();
      jettyServer.join();
    } catch (InterruptedException e) {
      System.out.println("InterupptedException: " + e.getMessage());
      System.out.println("Stack trace: " + e.getStackTrace());
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      System.out.println("Stack trace: " + e.getStackTrace());
    } finally {
      jettyServer.destroy();
    }
  }
}
