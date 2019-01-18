package com.mycompany.schoolme.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.mycompany.schoolme.domain.Student;
import com.mycompany.schoolme.service.dto.StudentDTO;
import com.mycompany.schoolme.service.dto.StudentDetailDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/student")
@Api(value = "search")
public class StudentResource {

  @GET
  @Path("/{studentId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public StudentDetailDTO details(@PathParam("studentId") Integer id) {
    return new Student().get(id);
  } 

  @GET
  @Path("search")
  @ApiOperation(value = "Searches for students by their name")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<StudentDTO> search(@QueryParam("first") String first, @QueryParam("last") String last) {
    Student s = new Student();
    return s.search(first, last);
  }
}
