package com.mycompany.schoolme.application;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mycompany.schoolme.cache.Cache;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

/**
 * Manages the build and break down of the application.
 */
@ApplicationPath("/api/v1")
public class SchoolMeApp extends ResourceConfig {

  static final Logger logger = LoggerFactory.getLogger(SchoolMeApp.class);

  public SchoolMeApp() {
    super(OpenApiResource.class, AcceptHeaderOpenApiResource.class,
        // register Jackson ObjectMapper resolver
        JacksonFeature.class);
    packages("com.mycompany.schoolme.resource");
    init();
  }

  private void init() {
    logger.info("School Me Example App");
    Cache.init();
  }
}
