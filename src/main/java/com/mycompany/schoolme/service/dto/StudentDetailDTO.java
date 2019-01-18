package com.mycompany.schoolme.service.dto;

import com.mycompany.schoolme.cache.ClassCache;
import com.mycompany.schoolme.domain.ClassDetail;
import com.mycompany.schoolme.domain.SchoolClass;
import com.mycompany.schoolme.domain.Student;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing a student.
 */
@XmlRootElement
public class StudentDetailDTO {

  private Integer id;
  private String first;
  private String last;
  private String email;
  private Double GPA;
  private List<StudentClassDTO> studentClasses;

  public StudentDetailDTO() {
    // Empty constructor needed for Jackson.
  }

  public StudentDetailDTO(Student student) {
    this.id = student.getId();
    this.first = student.getFirst();
    this.last = student.getLast();
    this.email = student.getEmail();
    setStudentClassesFromClassDetail(student.getStudentClasses());
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Double getGPA() {
    return GPA;
  }

  public void setGPA(Double gPA) {
    GPA = gPA;
  }

  public List<StudentClassDTO> getStudentClasses() {
    return studentClasses;
  }

  public void setStudentClasses(List<StudentClassDTO> studentClasses) {
    this.GPA =
        studentClasses.stream().mapToDouble(m -> m.getGrade()).summaryStatistics().getAverage();
    this.studentClasses = studentClasses;
  }

  public void setStudentClassesFromClassDetail(List<ClassDetail> studentClasses) {
    this.GPA =
        studentClasses.stream().mapToDouble(m -> m.getGrade()).summaryStatistics().getAverage();
    setStudentClasses(studentClasses.stream()
        .map(
            cd -> new StudentClassDTO(ClassCache.getByClassId(cd.getId()).getName(), cd.getGrade()))
        .collect(Collectors.toList()));
  }

}
