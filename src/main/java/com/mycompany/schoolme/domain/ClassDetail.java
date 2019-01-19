package com.mycompany.schoolme.domain;

/**
 * Information related to a student and a class.
 */
public class ClassDetail {

  private String id;
  private Double grade;
  
  /**
   * Instantiate an empty class detail.
   */
  public ClassDetail() {}

  /**
   * Get the class id.
   * 
   * @return the class id
   */
  public String getId() {
    return id;
  }

  /**
   * Set the class id.
   * 
   * @param id the class id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the students grade in the class.
   * 
   * @return the students grade
   */
  public Double getGrade() {
    return grade;
  }

  /**
   * Sets the students grade in a class.
   * 
   * @param grade the students grade
   */
  public void setGrade(Double grade) {
    this.grade = grade;
  }
  
}
