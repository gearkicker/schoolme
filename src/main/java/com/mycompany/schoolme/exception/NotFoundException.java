package com.mycompany.schoolme.exception;

/**
 * Exception that indicates data was not found
 * 
 * @author Doug Van Beynen
 *
 */
public class NotFoundException extends ApiException {
  
  private static final long serialVersionUID = -7555123176923912472L;
  
  @SuppressWarnings("unused")
  private int code;
 
  public NotFoundException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
