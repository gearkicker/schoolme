package com.mycompany.schoolme.exception;

/**
 * Exception that indicates data was not found
 */
public class NotFoundException extends ApiException {
  
  private static final long serialVersionUID = -7555123176923912472L;
  
  @SuppressWarnings("unused")
  private int code;

  /**
   * Instantiates a <tt>NotFoundException</tt> taking an http code and a descriptive message.
   * 
   * @param code corresponding http code
   * @param msg message describing the error
   */
  public NotFoundException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
