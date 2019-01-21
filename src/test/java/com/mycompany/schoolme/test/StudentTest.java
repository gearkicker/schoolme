package com.mycompany.schoolme.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import com.mycompany.schoolme.cache.Cache;
import com.mycompany.schoolme.domain.Student;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;

public class StudentTest {

  public StudentTest() {
    Cache.init();
  }

  @Test
  public void testGetStudentByIdObject() {
    Student student = new Student();
    StudentDetailDTO sDTO = student.get(0);
    assertNotNull(sDTO);
    assertEquals("John".toLowerCase(), sDTO.getFirst().toLowerCase());
  }

  @Test
  public void testStudentGPA() {
    Student student = new Student();
    StudentDetailDTO sDTO = student.get(1);
    assertNotNull(sDTO);
    assertEquals(new Double(2.75), sDTO.getGPA());
  }

  @Test
  public void testGetStudentByIdNotFound() {
    Student student = new Student();
    StudentDetailDTO sDTO = student.get(33);
    assertNull(sDTO);
  }

  @Test
  public void testStudentSearchBoth() {
    Student student = new Student();
    List<StudentDTO> students = student.search("john", "smith");
    assertNotNull(students);
    assertEquals("Smith", students.get(0).getLast());
  }

  @Test
  public void testStudentSearchFirst() {
    Student student = new Student();
    List<StudentDTO> students = student.search("john", null);
    assertNotNull(students);
    assertEquals("Smith", students.get(0).getLast());
  }

  @Test
  public void testStudentSearchLast() {
    Student student = new Student();
    List<StudentDTO> students = student.search(null, "smith");
    assertNotNull(students);
    assertEquals(2, students.size());
  }

  @Test
  public void testStudentSearchNotFound() {
    Student student = new Student();
    List<StudentDTO> students = student.search("van boxtel", null);
    assertNull(students);
  }

}
