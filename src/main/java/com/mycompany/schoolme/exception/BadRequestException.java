package com.mycompany.schoolme.exception;

public class BadRequestException extends ApiException {
  
  private static final long serialVersionUID = 1747759357047865875L;

  @SuppressWarnings("unused")
  private int code;
  
  public BadRequestException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
