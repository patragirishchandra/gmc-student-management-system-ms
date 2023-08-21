package com.gmc.studentmanagement.exception.enums;

/**
 * This class is the Enum class for ErrorCodeEnum, containing predefined error codes and their descriptions.
 * Each enum value represents a specific HTTP status code along with its associated error code.
 * This class provides a structured way to handle and communicate error codes within the application.
 *
 * @author Girish chandra Patra
 * @since 2023
 */

public enum ErrorCodeEnum {

    /**
     * The bad request.
     */
    BAD_REQUEST("GMC400"),

    /**
     * The record not found.
     */
    RECORD_NOT_FOUND("GMC404"),

    /**
     * The unprocessable entity.
     */
    UN_PROCESSABLE_ENTITY("GMC422"),

    /**
     * The too many requests.
     */
    TOO_MANY_REQUEST("GMC429"),

    /**
     * The internal server error code.
     */
    INTERNAL_SERVER_ERROR_CODE("GMC500"),

    /**
     * The service unavailable.
     */
    SERVICE_UNAVAILABLE("GMC503"),

    /**
     * The OK code.
     */
    OK_CODE("GMC200");

    /**
     * The error code.
     */
    private final String errorCode;

    /**
     * Instantiates a new error code enum.
     *
     * @param errorCode the error code
     */
    ErrorCodeEnum(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}

