package de.telran.onlineshopgarden.exception;

public class AccessForbiddenException extends RuntimeException {
  public AccessForbiddenException(String message) {
    super(message);
  }
}
