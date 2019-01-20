package com.mycompany.schoolme.application;

import java.io.IOException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mycompany.schoolme.cache.Cache;

/**
 * Manages the build and break down of the application.
 * @author dvanb
 *
 */
public class SchoolMeApp {
  
  static final Logger logger = LoggerFactory.getLogger(SchoolMeApp.class);

  public void run() {
    logger.info("School Me Example App");
    try {
      Cache.init();
    } catch (IOException e) {
      logger.error("Can not open student data file: " + e.getMessage());
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
    logger.info("School Me Example App");

    try {
      jettyServer.start();
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
}
