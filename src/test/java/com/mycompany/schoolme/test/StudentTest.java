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
    assertEquals(sDTO.getFirst().toLowerCase(), "John".toLowerCase());
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
    assertEquals(students.get(0).getLast(), "Smith");
  }

  @Test
  public void testStudentSearchFirst() {
    Student student = new Student();
    List<StudentDTO> students = student.search("john", null);
    assertNotNull(students);
    assertEquals(students.get(0).getLast(), "Smith");
  }

  @Test
  public void testStudentSearchLast() {
    Student student = new Student();
    List<StudentDTO> students = student.search(null, "smith");
    assertNotNull(students);
    assertEquals(students.size(), 2);
  }

  @Test
  public void testStudentSearchNotFound() {
    Student student = new Student();
    List<StudentDTO> students = student.search("van boxtel", null);
    assertNull(students);
  }

}
