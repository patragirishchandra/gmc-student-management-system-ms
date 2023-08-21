package com.gmc.studentmanagement.exception;
/**
 * This class is the Exception class for InvalidRequestException.
 *
 * @author Girish Chandra Patra
 * @since 2023
 */
public class InvalidRequestException extends RuntimeException  {

    /**
     * The error details.
     */
    private final transient ErrorDetails errorDetails;

    /**
     * Instantiates a new invalid request exception.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @param metadata     the metadata
     */
    public InvalidRequestException(final String errorCode, final String errorMessage,
                                   final String metadata) {
        super(errorMessage);
        this.errorDetails = new ErrorDetails(errorCode, errorMessage, metadata);
    }

    /**
     * Instantiates a new invalid request exception.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @param metadata     the metadata
     * @param level        the level
     */
    public InvalidRequestException(final String errorCode, final String errorMessage,
                                   final String metadata,
                                   final String level) {
        super(errorMessage);
        this.errorDetails = new ErrorDetails(errorCode, errorMessage, metadata, level);
    }

    /**
     * Instantiates a new invalid request exception.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public InvalidRequestException(final String errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorDetails = new ErrorDetails(errorCode, errorMessage);
    }

    /**
     * Instantiates a new invalid request exception.
     * @param errorDetails
     */
    public InvalidRequestException(ErrorDetails errorDetails) {
        super();
        this.errorDetails = errorDetails;
    }

    /**
     * Gets the error details.
     *
     * @return the error details
     */
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

}