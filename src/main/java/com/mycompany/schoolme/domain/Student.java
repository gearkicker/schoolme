package com.mycompany.schoolme.domain;

import java.util.List;
import java.util.stream.Collectors;
import com.mycompany.schoolme.cache.StudentCache;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;

public class Student {

  private Integer id;
  private String first;
  private String last;
  private String email;
  private List<ClassDetail> studentClasses;

  public Student() {}

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<ClassDetail> getStudentClasses() {
    return studentClasses;
  }

  public void setStudentClasses(List<ClassDetail> studentClasses) {
    this.studentClasses = studentClasses;
  }

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

  public StudentDetailDTO get(Integer id) {
    Student s = StudentCache.get(id);
    if (null != s) {
      return new StudentDetailDTO(StudentCache.get(id));      
    }
    return null;
  }
}
