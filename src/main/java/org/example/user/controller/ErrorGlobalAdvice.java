package org.example.user.controller;

import org.slf4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.UnexpectedTypeException;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
class ErrorGlobalAdvice {

  private final Logger logger = getLogger(this.getClass());

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  void internalError(final Throwable e) {
    logger.error("Internal error: " + INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler({IllegalStateException.class, Exception.class})
  void unhandledError(final Exception e) {
    logger.error("Unhandled exception: " + INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class,
      MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
      UnexpectedTypeException.class})
  void illegalArgumentError(final Exception e) {
    logger.error("Illegal argument: " + BAD_REQUEST.getReasonPhrase(), e);
  }
}
