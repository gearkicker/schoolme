package com.mycompany.schoolme.domain;

import java.util.List;
import java.util.Map;
import java.sql.DriverManager;

public class SchoolDB {

  private List<Student> students;
  private Map<String, String> classes;

  public SchoolDB() {}

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public Map<String, String> getClasses() {
    return classes;
  }

  public void setClasses(Map<String, String> classes) {
    this.classes = classes;
  }

  public void insertTables(DriverManager db, SchoolDB tables) {

  }
}
