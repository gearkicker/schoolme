package com.mycompany.schoolme.domain;

import java.util.List;
import java.util.stream.Collectors;
import com.mycompany.schoolme.cache.StudentCache;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;

/**
 * A representation of a student within the application.
 */
public class Student {

  private Integer id;
  private String first;
  private String last;
  private String email;
  private List<ClassDetail> studentClasses;

  /**
   * Instantiates an empty student.
   */
  public Student() {}

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
   * Get the email address of the student
   * 
   * @return the students email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the email address of the student.
   * 
   * @param email the email address of the student.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Get the list of student classes.
   * 
   * @return the list of student classes
   * @see ClassDetail
   */
  public List<ClassDetail> getStudentClasses() {
    return studentClasses;
  }

  /**
   * Set the list of student classes.
   * 
   * @param studentClasses the students classes as a list
   * @see ClassDetail
   */

  public void setStudentClasses(List<ClassDetail> studentClasses) {
    this.studentClasses = studentClasses;
  }

  /**
   * Search for a student by first name, last name or both,
   * 
   * @param first the students first name
   * @param last the students last name
   * @return a list of students
   */
  public List<StudentDTO> search(String first, String last) {
    List<Student> students = null;

    if (first == null && last == null) {
      return null;
    }

    if (first != null && last != null) {
      students = StudentCache.getByFirstLast(first, last);
    } else if (first != null) {
      students = StudentCache.getByFirst(first);
    } else if (last != null) {
      students = StudentCache.getByLast(last);
    }
    if (students.size() == 0) {
      return null;
    }

    return students.stream().map(s -> new StudentDTO(s)).collect(Collectors.toList());
  }

  /**
   * Get a student by id.
   * 
   * @param id the id of a student
   * @return the REST version of the student object or null if the student is not found
   */
  public StudentDetailDTO get(Integer id) {
    Student s = StudentCache.get(id);
    if (null != s) {
      return new StudentDetailDTO(StudentCache.get(id));
    }
    return null;
  }
}
