package com.mycompany.schoolme.resource;

import com.mycompany.schoolme.exception.ApiException;
import com.mycompany.schoolme.exception.NotFoundException;
import com.mycompany.schoolme.exception.BadRequestException;
import com.mycompany.schoolme.domain.ApiResponse;

import javax.ws.rs.ext.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Builds an http response based on an exception that was thrown.
 */
@Provider
public class SchoolMeExceptionMapper implements ExceptionMapper<Exception> {
  
  /**
   * Map the exception to a http response code.
   */
  public Response toResponse(Exception exception) {
    if (exception instanceof javax.ws.rs.WebApplicationException) {
      javax.ws.rs.WebApplicationException e = (javax.ws.rs.WebApplicationException) exception;
      return Response
          .status(e.getResponse().getStatus())
          .entity(new com.mycompany.schoolme.domain.ApiResponse(e.getResponse().getStatus(),
              exception.getMessage())).build();
    } else if (exception instanceof com.fasterxml.jackson.core.JsonParseException) {
      return Response.status(400)
          .entity(new ApiResponse(400, "bad input")).build();
    } else if (exception instanceof NotFoundException) {
      return Response
          .status(Status.NOT_FOUND)
          .entity(new ApiResponse(ApiResponse.ERROR, exception
              .getMessage())).build();
    } else if (exception instanceof BadRequestException) {
      return Response
          .status(Status.BAD_REQUEST)
          .entity(new ApiResponse(ApiResponse.ERROR, exception
              .getMessage())).build();
    } else if (exception instanceof ApiException) {
      return Response
          .status(Status.BAD_REQUEST)
          .entity(new ApiResponse(ApiResponse.ERROR, exception
              .getMessage())).build();
    } else {
      return Response.status(500)
          .entity(new ApiResponse(500, "bad state"))
          .build();
    }
  }
}