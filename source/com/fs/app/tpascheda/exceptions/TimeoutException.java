package com.fs.app.tpascheda.exceptions;

public class TimeoutException
    extends Exception {
  public TimeoutException(String message) {
    super(message);
  }

  /**
   * timeout
   */
  public TimeoutException() {
    super(
        "Session timeout. Please reenter the application from the TPA Support Site");
  }
}
