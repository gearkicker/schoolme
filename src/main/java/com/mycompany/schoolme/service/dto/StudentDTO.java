package com.mycompany.schoolme.service.dto;

import com.mycompany.schoolme.domain.Student;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing a student.
 */
@XmlRootElement
public class StudentDTO {

  private Integer id;
  private String first;
  private String last;
  private Double GPA;

  public StudentDTO() {
    // Empty constructor needed for Jackson.
  }

  public StudentDTO(Student student) {
    this.id = student.getId();
    this.first = student.getFirst();
    this.last = student.getLast();
    this.GPA = student.getStudentClasses().stream().mapToDouble(m -> m.getGrade())
        .summaryStatistics().getAverage();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public Double getGPA() {
    return GPA;
  }

  public void setGPA(Double gPA) {
    GPA = gPA;
  }
}
