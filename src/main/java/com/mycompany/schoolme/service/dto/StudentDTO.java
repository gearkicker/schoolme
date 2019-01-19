package com.mycompany.schoolme.service.dto;

import com.mycompany.schoolme.domain.Student;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing a student in a lightweight form usually returned in lists. This DTO is used
 * when sending JSON versions of this object through REST end points.
 */
@XmlRootElement
public class StudentDTO {

  private Integer id;
  private String first;
  private String last;
  private Double GPA;

  /**
   * Instantiates a new <tt>StudentDTO</tt> object
   * 
   */
  public StudentDTO() {
    // Empty constructor needed for Jackson.
  }

  /**
   * Instantiates a StudentDTO object from a <tt>Student</tt> domain object.
   * 
   * @param student the <tt>Student</tt> object
   * @see com.mycompany.schoolme.domain.Student
   */
  public StudentDTO(Student student) {
    this.id = student.getId();
    this.first = student.getFirst();
    this.last = student.getLast();
    this.GPA = student.getStudentClasses().stream().mapToDouble(m -> m.getGrade())
        .summaryStatistics().getAverage();
  }

  /**
   * Get the students id.
   * 
   * @return the id of the student
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the students id.
   * 
   * @param id the id of the student
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the first name of the student.
   * 
   * @return the students first name
   */
  public String getFirst() {
    return first;
  }

  /**
   * Set the first name of the student.
   * 
   * @param first the students first name
   */
  public void setFirst(String first) {
    this.first = first;
  }

  /**
   * Get the last name of the student
   * 
   * @return the students last name
   */
  public String getLast() {
    return last;
  }

  /**
   * Set the last name of the student.
   * 
   * @param last the last name of the student
   */
  public void setLast(String last) {
    this.last = last;
  }

  /**
   * Get the students GPA fot this class
   * 
   * @return the students GPA
   */
  public Double getGPA() {
    return GPA;
  }

  /**
   * Set the students GPA
   * 
   * @param GPA the students GPA for the class
   */
  public void setGPA(Double GPA) {
    GPA = GPA;
  }
}
