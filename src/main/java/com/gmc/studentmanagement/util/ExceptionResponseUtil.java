package com.gmc.studentmanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmc.studentmanagement.filter.LoggingPrependFilter;
import com.gmc.studentmanagemet.resource.EmptyDataResponse;
import com.gmc.studentmanagemet.resource.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.BAD_REQUEST;
import static com.gmc.studentmanagement.exception.enums.ErrorLevelEnum.ERROR;
import static com.gmc.studentmanagement.util.ExceptionUtil.prepareOutputMessage;

/**
 * This class provides utility methods for building EmptyDataResponse from exceptions.
 *
 * @author Girish Chandra Patra
 */
public class ExceptionResponseUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionResponseUtil.class);
    /**
     * Builds an EmptyDataResponse from the given exception.
     *
     * @param exception the exception
     * @return an EmptyDataResponse containing notifications based on the exception
     */
    public static EmptyDataResponse buildEmptyDataResponseFromException(Exception exception) {
        List<Notification> notifications = prepareNotificationsFromException(exception);
        return createEmptyDataResponseWithNotifications(notifications);
    }

    /**
     * Prepares notifications from the given exception.
     *
     * @param exception the exception
     * @return a list of notifications based on the exception
     */
    private static List<Notification> prepareNotificationsFromException(Exception exception) {
        String errorMessage = (exception.getCause() != null) ? exception.getCause().toString() : exception.getMessage();
        return prepareOutputMessage(exception, ERROR.getErrorLevel(), BAD_REQUEST.getErrorCode(),
                MDC.get(LoggingPrependFilter.MDC_PREFIX_NAME), errorMessage);
    }

    /**
     * Creates an EmptyDataResponse with the given list of notifications.
     *
     * @param notifications the list of notifications
     * @return an EmptyDataResponse with the provided notifications
     */
    private static EmptyDataResponse createEmptyDataResponseWithNotifications(List<Notification> notifications) {
        EmptyDataResponse emptyDataResponse = new EmptyDataResponse();
        emptyDataResponse.setNotifications(notifications);
        return emptyDataResponse;
    }

    /**
     * Converts the given object to its JSON representation.
     *
     * @param object the object to be converted
     * @return a ResponseEntity containing the JSON representation of the object
     * @throws JsonProcessingException if an error occurs during JSON processing
     */
    public static ResponseEntity<String> convertObjectToJson(Object object)
            throws JsonProcessingException {
        LOG.info("convertObjectToJson Method, is object is having some data: {}", object != null);

        if (object == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);

        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }
}

