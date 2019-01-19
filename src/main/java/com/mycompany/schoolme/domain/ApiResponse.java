package com.mycompany.schoolme.domain;

import javax.xml.bind.annotation.XmlTransient;

/**
 * A class used to encapsulate REST responses usually associated with error handling. 
 */
@javax.xml.bind.annotation.XmlRootElement
public class ApiResponse {
  public static final int ERROR = 1;
  public static final int WARNING = 2;
  public static final int INFO = 3;
  public static final int OK = 4;
  public static final int TOO_BUSY = 5;

  int code;
  String type;
  String message;

  /**
   * Instantiates an empty api response
   */
  public ApiResponse() {}

  /**
   * Instantiates an api response with an http code and a detailed reason for that code.
   * 
   * @param code the http response code
   * @param message reason for the code
   */
  public ApiResponse(int code, String message) {
    this.code = code;
    switch (code) {
      case ERROR:
        setType("error");
        break;
      case WARNING:
        setType("warning");
        break;
      case INFO:
        setType("info");
        break;
      case OK:
        setType("ok");
        break;
      case TOO_BUSY:
        setType("too busy");
        break;
      default:
        setType("unknown");
        break;
    }
    this.message = message;
  }

  /**
   * Gets the http code associated with the response.
   * 
   * @return the http code
   */
  @XmlTransient
  public int getCode() {
    return code;
  }

  /**
   * Sets the http code associated with the response
   * 
   * @param code the http code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets the content type associated with the response.
   * 
   * @return the content type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the content type of the response
   * 
   * @param type the content type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the message associated with the response.
   * 
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message associated with the response.
   * 
   * @param message the response message
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
