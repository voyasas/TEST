package com.fs.app.tpascheda.exceptions;

public class TPDupsException   extends Exception {
  public TPDupsException(String message) {
    super(message);
  }

  /**
   * timeout
   */
  public TPDupsException() {
    super(
        "Duplicate Entries in Sales Logix for the TPA Code. Please contact the TPAWebmaster.");
  }
}
