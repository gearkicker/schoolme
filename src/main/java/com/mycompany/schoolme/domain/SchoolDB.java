package com.mycompany.schoolme.domain;

import java.util.List;
import java.util.Map;

/**
 * A data definition describing the school. The definition describes students and classes.
 */
public class SchoolDB {

  private List<Student> students;
  private Map<String, String> classes;

  /**
   * Instantiates an empty object. Needed when doing JSON exports and imports.
   */
  public SchoolDB() {}

  /**
   * Gets a list of students.
   * 
   * @return a list of students
   * @see Student
   */
  public List<Student> getStudents() {
    return students;
  }

  /**
   * Sets a list of students.
   * 
   * @param students the list of students
   * @see Student
   */
  public void setStudents(List<Student> students) {
    this.students = students;
  }

  /**
   * Gets a map of classes. The map contains the class id and the class name.
   * 
   * @return a map of classes
   */
  public Map<String, String> getClasses() {
    return classes;
  }

  /**
   * Sets a map of classes. The map contains the class id and the class name.
   * 
   * @param classes a map containing the classes(class id, class name)
   */
  public void setClasses(Map<String, String> classes) {
    this.classes = classes;
  }
}
