package com.mycompany.schoolme.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing detail about a students class.
 */
@XmlRootElement
public class StudentClassDTO {

  private String name;
  private Double grade;

  /**
   * Initiates an empty StudentClassDTO. One reason this is needed is when creating StudentclassDTO
   * objects from JSON data.
   */
  public StudentClassDTO() {}

  /**
   * Instantiates a StudentClassDTO with data.
   * 
   * @param name the name of the student
   * @param grade the grade of the student
   */
  public StudentClassDTO(String name, Double grade) {
    this.name = name;
    this.grade = grade;
  }

  /**
   * Get the students class name.
   * 
   * @return name of the students class
   */
  public String getName() {
    return name;
  }

  /**
   * Set the students class name.
   * 
   * @param name the students class name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the grade of a students class
   * 
   * @return the students grade in the class
   */
  public Double getGrade() {
    return grade;
  }

  /**
   * Set the grade of a students class.
   * 
   * @param grade grade of the students class
   */
  public void setGrade(Double grade) {
    this.grade = grade;
  }

}
