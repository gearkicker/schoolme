package com.mycompany.schoolme.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.mycompany.schoolme.domain.Student;
import com.mycompany.schoolme.exception.NotFoundException;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * REST end points that handles REST calls relating to a student.
 */
@Path("/student")
@Api(value = "search")
public class StudentResource {

  /**
   * A REST end point that gets a student by id
   * 
   * @param id the id of a student
   * @return a students detail in xlm or json format
   * @throws NotFoundException if there are no students found
   */
  @GET
  @Path("/{studentId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public StudentDetailDTO details(@PathParam("studentId") Integer id) throws NotFoundException {
    StudentDetailDTO sDTO = new Student().get(id);
    if (null != sDTO) {
      return sDTO;
    } else {
      throw new NotFoundException(404, "Student not found");
    }
  }

  /**
   * A REST end point that performs a lookup of a student by first name, last name or both and
   * returns a list of matching students. The resulting list may contain one or more rows.
   * 
   * @param first first name of the student to search by
   * @param last last name of the student to search by
   * @return a list of students in xml or json format
   * @throws NotFoundException if there are no students found
   */
  @GET
  @Path("search")
  @ApiOperation(value = "Searches for students by their name")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Student not found")})
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<StudentDTO> search(@QueryParam("first") String first, @QueryParam("last") String last)
      throws NotFoundException {
    List<StudentDTO> students = new Student().search(first, last);
    if (null != students) {
      return students;
    } else {
      throw new NotFoundException(404, "Student not found");
    }
  }
}
