package com.mycompany.schoolme;

import org.eclipse.jetty.server.Server;
import com.mycompany.schoolme.cache.Cache;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import java.io.IOException;
import java.net.URI;

public class App {

  private static final URI BASE_URI = URI.create("http://localhost:8080/");

  public static void main(String[] args) throws Exception {
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
        context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
    jerseyServlet.setInitOrder(1);

    // Tells the Jersey Servlet which REST service/class to load.
    jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
        "io.swagger.jaxrs.listing;com.mycompany.schoolme.resource");

    // Allow jersey to us Jackson for JSON
    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
        "org.glassfish.jersey.jackson.JacksonFeature");

    ServletHolder swaggerServlet = context.addServlet(io.swagger.jersey.config.JerseyJaxrsConfig.class, "/swaggger-core");
    swaggerServlet.setInitOrder(2);
    swaggerServlet.setInitParameter("api.version", "1.0.0");
    swaggerServlet.setInitParameter("swagger.api.basepath", "http://localhost:8080/api");

    try {
      jettyServer.start();
      jettyServer.join();
    } finally {
      jettyServer.destroy();
    }
  }
}
