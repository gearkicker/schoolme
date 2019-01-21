package com.mycompany.schoolme.test;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.glassfish.jersey.server.ResourceConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.mycompany.schoolme.application.SchoolMeApp;
import com.mycompany.schoolme.resource.SchoolMeExceptionMapper;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;

@RunWith(ConcurrentRunner.class)
public class SchoolMeRestTest extends JerseyTest {

  @Override
  protected ResourceConfig configure() {
    return new SchoolMeApp().register(SchoolMeExceptionMapper.class);
  }

  @Test
  public void testGetStudentByIdString() {
    WebTarget target = target();
    String student = target.path("student/1").request("application/json").get(String.class);
    assertTrue(student.contains("Smith"));
  }

  @Test
  public void testGetStudentByIdObject() {
    WebTarget target = target();
    StudentDetailDTO student =
        target.path("student/1").request("application/json").get(StudentDetailDTO.class);
    assertNotNull(student);
  }

  @Test
  public void testGetStudentByIdNotFound() {
    WebTarget target = target();
    Response response = target.path("student/33").request("application/json").get();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }

  @Test
  public void testStudentSearchBoth() {
    WebTarget target = target();
    List<StudentDTO> students =
        target.path("student/search").queryParam("first", "john").queryParam("last", "smith")
            .request("application/json").get(new GenericType<List<StudentDTO>>() {});
    assertNotNull(students);
    assertEquals("Smith", students.get(0).getLast());
  }

  @Test
  public void testStudentSearchFirst() {
    WebTarget target = target();
    List<StudentDTO> students = target.path("student/search").queryParam("first", "john")
        .request("application/json").get(new GenericType<List<StudentDTO>>() {});
    assertNotNull(students);
    assertEquals("Smith", students.get(0).getLast());
  }

  @Test
  public void testStudentSearchLast() {
    WebTarget target = target();
    List<StudentDTO> students = target.path("student/search").queryParam("last", "smith")
        .request("application/json").get(new GenericType<List<StudentDTO>>() {});
    assertNotNull(students);
    assertEquals(2, students.size());
  }

  @Test
  public void testStudentSearchNotFound() {
    WebTarget target = target();
    Response response = target.path("student/search").queryParam("last", "van beynen")
        .request("application/json").get();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }

  @Test
  public void testStudentSearchNoParams() {
    WebTarget target = target();
    Response response = target.path("student/search").request("application/json").get();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }

}
