package com.mycompany.schoolme.domain;

/**
 * Holds the information about a class. Contains the class id and the name of the class.
 */
public class SchoolClass {

  private String id;
  private String name;

  /**
   * Instantiates a school class. Used when importing and exporting JSON data.
   */
  public SchoolClass() {}

  /**
   * Instantiates a school class.
   * 
   * @param id the class id
   * @param name the class name
   */
  public SchoolClass(String id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Get the id of a class
   * 
   * @return the class id
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
