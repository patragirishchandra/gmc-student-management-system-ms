package com.gmc.studentmanagement.filter;

import static com.gmc.studentmanagement.util.ExceptionResponseUtil.buildEmptyDataResponseFromException;
import static com.gmc.studentmanagement.util.ExceptionResponseUtil.convertObjectToJson;

import com.gmc.studentmanagemet.resource.EmptyDataResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


/**
 * This class is the LoggingPrependFilter class for tracking Logging info.
 */
@Component
public class LoggingPrependFilter extends GenericFilterBean {

  /**
   * The Constant HEADER_TRACE_ID.
   */
  public static final String HEADER_TRACE_ID = "x-request.id";

  /**
   * The Constant MDC_PREFIX_NAME.
   */
  public static final String MDC_PREFIX_NAME = "xRequestId";

  /**
   * Do filter.
   *
   * @param servletRequest  the servlet request
   * @param servletResponse the servlet response
   * @param filterChain     the filter chain
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain)
      throws IOException {
    final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    String logTraceIdentification = null;

    for (Enumeration<String> e = httpRequest.getHeaderNames(); e.hasMoreElements(); ) {
      String nextHeaderName = e.nextElement();

      if (nextHeaderName.equalsIgnoreCase(HEADER_TRACE_ID)) {
        logTraceIdentification = httpRequest.getHeader(HEADER_TRACE_ID);
        break;
      }
    }

    if (logTraceIdentification != null) {
      MDC.put(MDC_PREFIX_NAME, logTraceIdentification);
    } else {
      MDC.put(MDC_PREFIX_NAME, UUID.randomUUID().toString());
    }
    try {
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (Exception exception) {
      EmptyDataResponse exceptionResponse = buildEmptyDataResponseFromException(exception);
      httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
      httpResponse.setContentType("application/json");
      httpResponse.getWriter().write(String.valueOf(convertObjectToJson(exceptionResponse)));
    }
  }


}
