package com.gmc.studentmanagement.exception;


import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.BAD_REQUEST;
import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.INTERNAL_SERVER_ERROR_CODE;
import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.SERVICE_UNAVAILABLE;
import static com.gmc.studentmanagement.exception.enums.ErrorLevelEnum.ERROR;
import static com.gmc.studentmanagement.exception.enums.ErrorLevelEnum.INFO;
import static com.gmc.studentmanagement.exception.enums.ErrorLevelEnum.WARN;
import static com.gmc.studentmanagement.util.ExceptionUtil.prepareOutputMessage;

import com.gmc.studentmanagement.filter.LoggingPrependFilter;
import com.gmc.studentmanagemet.resource.EmptyDataResponse;
import com.gmc.studentmanagemet.resource.Notification;
import java.lang.reflect.Type;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;


/**
 * This class is the GlobalControllerAdvice class responsible for handling global exceptions that
 * can occur in controllers. It provides exception handling methods for various error scenarios and
 * generates appropriate response messages. The class also implements the RequestBodyAdvice
 * interface for handling request body read operations.
 *
 * @author Girish Chandra Patra
 */
@RestControllerAdvice(basePackages = "com.gmc")
public class GlobalControllerAdvice implements RequestBodyAdvice {

  /**
   * The Constant REQ_CONST.
   */
  private static final String REQ_CONST = " for request ";

  /**
   * The Constant GLOBAL_CONTROLLER_ADVICE_LOGGER.
   */
  private static final Logger GLOBAL_CONTROLLER_ADVICE_LOGGER = LoggerFactory
      .getLogger(GlobalControllerAdvice.class);

  /**
   * The body.
   */
  private String body;


  /**
   * This handler is for BAD REQUEST.
   *
   * @param exception  the exception
   * @param request    the request
   * @param webRequest the web request
   * @return the empty data response
   */

  @ExceptionHandler({InvalidRequestException.class, ConstraintViolationException.class,
      DateTimeParseException.class, MissingRequestHeaderException.class,
      MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
      MethodArgumentNotValidException.class, IllegalArgumentException.class,
      PropertyReferenceException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public EmptyDataResponse handleBadRequest(Exception exception, HttpServletRequest request,
      WebRequest webRequest) {

    List<Notification> notifications = prepareOutputMessage(exception, ERROR.getErrorLevel(),
        BAD_REQUEST.getErrorCode(), MDC.get(LoggingPrependFilter.MDC_PREFIX_NAME),
        exception.getMessage());
    GLOBAL_CONTROLLER_ADVICE_LOGGER
        .info(notifications.get(0).getMessage(), REQ_CONST, body, exception);
    EmptyDataResponse exceptionResponse = new EmptyDataResponse();
    exceptionResponse.setNotifications(notifications);
    return exceptionResponse;
  }

  /**
   * This handler is for INTERNAL SERVER ERROR.
   *
   * @param exception  the exception
   * @param request    the request
   * @param webRequest the web request
   * @return the empty data response
   */
  @ExceptionHandler({Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public EmptyDataResponse handleServerError(Exception exception, HttpServletRequest request,
      WebRequest webRequest) {

    List<Notification> notifications = prepareOutputMessage(exception, ERROR.getErrorLevel(),
        INTERNAL_SERVER_ERROR_CODE.getErrorCode(),
        MDC.get(LoggingPrependFilter.MDC_PREFIX_NAME), exception.getMessage());
    GLOBAL_CONTROLLER_ADVICE_LOGGER
        .error(notifications.get(0).getMessage(), REQ_CONST, body, exception);
    EmptyDataResponse exceptionResponse = new EmptyDataResponse();
    exceptionResponse.setNotifications(notifications);
    return exceptionResponse;
  }

  /**
   * This handler is for SERVICE UNAVAILABLE.
   *
   * @param exception  the exception
   * @param request    the request
   * @param webRequest the web request
   * @return EmptyDataResponse
   */
  @ExceptionHandler({ServiceUnavailableException.class})
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public EmptyDataResponse handleServiceUnavailableException(
      ServiceUnavailableException exception,
      HttpServletRequest request, WebRequest webRequest) {

    List<Notification> notifications = prepareOutputMessage(exception, INFO.getErrorLevel(),
        SERVICE_UNAVAILABLE.getErrorCode(),
        MDC.get(LoggingPrependFilter.MDC_PREFIX_NAME), exception.getMessage());
    GLOBAL_CONTROLLER_ADVICE_LOGGER
        .error(notifications.get(0).getMessage(), REQ_CONST, body, exception);
    EmptyDataResponse exceptionResponse = new EmptyDataResponse();
    exceptionResponse.setNotifications(notifications);
    return exceptionResponse;
  }

  /**
   * This handler is for NOT FOUND.
   *
   * @param exception  the exception
   * @param request    the request
   * @param webRequest the web request
   * @return the empty data response
   */
  @ExceptionHandler({RecordNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public EmptyDataResponse handleRecordNotFoundException(RecordNotFoundException exception,
      HttpServletRequest request, WebRequest webRequest) {

    List<Notification> notifications = prepareOutputMessage(exception, WARN.getErrorLevel(),
        exception.getErrorDetails().getErrorCode(),
        MDC.get(LoggingPrependFilter.MDC_PREFIX_NAME),
        exception.getErrorDetails().getMetadata());
    GLOBAL_CONTROLLER_ADVICE_LOGGER
        .warn(notifications.get(0).getMessage(), REQ_CONST, body, exception);
    EmptyDataResponse exceptionResponse = new EmptyDataResponse();
    exceptionResponse.setNotifications(notifications);
    return exceptionResponse;
  }

  /**
   * Supports.
   *
   * @param methodParameter the method parameter
   * @param targetType      the target type
   * @param converterType   the converter type
   * @return true, if successful
   */
  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  /**
   * Before body read.
   *
   * @param inputMessage  the input message
   * @param parameter     the parameter
   * @param targetType    the target type
   * @param converterType the converter type
   * @return the http input message
   */
  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return inputMessage;
  }

  /**
   * After body read.
   *
   * @param body          the body
   * @param inputMessage  the input message
   * @param parameter     the parameter
   * @param targetType    the target type
   * @param converterType the converter type
   * @return the object
   */
  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
      MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return createNewBodyObject(body);
  }


  /**
   * Handle empty body.
   *
   * @param body          the body
   * @param inputMessage  the input message
   * @param parameter     the parameter
   * @param targetType    the target type
   * @param converterType the converter type
   * @return the object
   */
  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage,
      MethodParameter parameter,
      Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return createNewBodyObject(body);
  }

  private Object createNewBodyObject(Object body) {
    if (body == null) {
      body = new Object();
    }
    return body;
  }
}
