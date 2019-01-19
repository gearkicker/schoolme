package com.mycompany.schoolme.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing detail about a student class.
 */
@XmlRootElement
public class StudentClassDTO {

  private String name;
  private Double grade;

  public StudentClassDTO() {}

  public StudentClassDTO(String name, Double grade) {
    this.name = name;
    this.grade = grade;
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

  /**
   * Get the grade of a class
   * 
   * @return the students grade in the class
   */
  public Double getGrade() {
    return grade;
  }

  /**
   * Set the grade for a class.
   * 
   * @param grade grade of the class
   */
  public void setGrade(Double grade) {
    this.grade = grade;
  }

}
