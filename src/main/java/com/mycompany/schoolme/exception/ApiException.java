package com.mycompany.schoolme.exception;

/**
 * Class that encapsulates all API exceptions
 */
public class ApiException extends Exception {
 
  private static final long serialVersionUID = -5855737292758165545L;
  
  @SuppressWarnings("unused")
  private int code;

  /**
   * Instantiates an api exception with an http code and a detailed message. 
   * 
   * @param code Exception code - should match HTTP Response Code
   * @param msg message indicating what the exception is
   */
  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }
}
