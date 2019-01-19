package com.mycompany.schoolme.service.dto;

import com.mycompany.schoolme.cache.ClassCache;
import com.mycompany.schoolme.domain.ClassDetail;
import com.mycompany.schoolme.domain.Student;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A DTO representing detail about a student. This DTO is used when sending detailed JSON versions
 * of a student through an end point.
 */
@XmlRootElement
public class StudentDetailDTO extends StudentDTO {

  private String email;
  private List<StudentClassDTO> studentClasses;

  /**
   * Initiates an empty StudentDetailDTO. On reason this is needed is when creating StudentDetailDTO
   * objects from JSON data.
   */
  public StudentDetailDTO() {
    super();
  }

  /**
   * Instantiates a detailed object of a student from a <tt>Student</tt> object. This is
   * primarily used when retrieving an object from a data store.
   * 
   * @param student the <tt>Student</tt> object
   * @see com.mycompany.schoolme.domain.Student
   */
  public StudentDetailDTO(Student student) {
    super(student);
    this.email = student.getEmail();
    setStudentClassesFromClassDetail(student.getStudentClasses());
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
   * @see com.mycompany.schoolme.service.dto.StudentClassDTO
   */
  @XmlElementWrapper(name = "studentClasses")
  @XmlElement(name = "studentClassDTO")
  public List<StudentClassDTO> getStudentClasses() {
    return studentClasses;
  }

  /**
   * Set the list of student classes.
   * 
   * @param studentClasses the students classes as a list
   * @see com.mycompany.schoolme.service.dto.StudentClassDTO
   */
  public void setStudentClasses(List<StudentClassDTO> studentClasses) {
    super.setGPA(
        studentClasses.stream().mapToDouble(m -> m.getGrade()).summaryStatistics().getAverage());
    this.studentClasses = studentClasses;
  }

  /*
   * This is an internal helper method that converts a list of <tt>ClassDetail</tt> into a list of
   * StudentClassDTO and then sets the list in the object.
   * 
   */
  private void setStudentClassesFromClassDetail(List<ClassDetail> studentClasses) {
    setStudentClasses(studentClasses.stream()
        .map(
            cd -> new StudentClassDTO(ClassCache.getByClassId(cd.getId()).getName(), cd.getGrade()))
        .collect(Collectors.toList()));
  }

}
