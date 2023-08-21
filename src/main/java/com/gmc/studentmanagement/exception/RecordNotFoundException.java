package com.gmc.studentmanagement.exception;

import java.util.List;


/**
 * This class is the RecordNotFoundException class for handling No data response.
 *
 * @author Girish Chandra Patra
 */
public class RecordNotFoundException extends RuntimeException {

    /**
     * The error details.
     */
    private final transient ErrorDetails errorDetails;

    /**
     * Instantiates a new record not found exception.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public RecordNotFoundException(final String errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorDetails = new ErrorDetails(errorCode, errorMessage);
    }

    /**
     * Instantiates a new record not found exception.
     * @param errorDetails
     */
    public RecordNotFoundException(ErrorDetails errorDetails) {
        super();
        this.errorDetails = errorDetails;
    }

    /**
     * Instantiates a new record not found exception.
     *
     * @param errorCode     the error code
     * @param errorMessage  the error message
     * @param singletonList the singleton list
     */
    public RecordNotFoundException(String errorCode, String errorMessage,
                                   List<String> singletonList) {
        this.errorDetails = new ErrorDetails(errorCode, errorMessage, singletonList.toString());
    }

    public RecordNotFoundException(final String errorCode, final String errorMessage,
                                   final String metadata) {
        super(errorMessage);
        this.errorDetails = new ErrorDetails(errorCode, errorMessage, metadata);
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
