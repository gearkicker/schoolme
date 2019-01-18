package com.mycompany.schoolme.domain;

public class SchoolClass {

  private String id;
  private String name;

  public SchoolClass() {}

  public SchoolClass(String id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Get the id of a class
   * 
   * @return
   */
  public String getId() {
    return id;
  }

  /**
   * Set the id for a class.
   * 
   * @param id id of the class
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get the class name.
   * 
   * @return name of the class
   */
  public String getName() {
    return name;
  }

  /**
   * Set the class name.
   * 
   * @param name Class Name
   */
  public void setName(String name) {
    this.name = name;
  }
}
