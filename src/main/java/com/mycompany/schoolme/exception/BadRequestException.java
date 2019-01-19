package com.mycompany.schoolme.exception;

/**
 * Exception that indicates there was a bad http request
 */
public class BadRequestException extends ApiException {
  
  private static final long serialVersionUID = 1747759357047865875L;

  @SuppressWarnings("unused")
  private int code;
 
  /**
   * Instantiates a <tt>BadRequestException</tt> taking an http code and a descriptive message.
   * 
   * @param code corresponding http code
   * @param msg message describing the error
   */
  public BadRequestException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
