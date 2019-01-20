package com.mycompany.schoolme;

import com.mycompany.schoolme.application.SchoolMeApp;

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
    SchoolMeApp theApp = new SchoolMeApp();
    theApp.run();
  }
}
