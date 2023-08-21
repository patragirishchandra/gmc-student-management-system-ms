package com.gmc.studentmanagement.exception;

/**
 * This class is the ErrorDetails class.
 *
 * @author Girish Chandra patra
 */
public class ErrorDetails {

    /**
     * The error code.
     */
    private String errorCode;

    /**
     * The error message.
     */
    private String errorMessage;

    /**
     * The metadata.
     */
    private String metadata;

    /**
     * The error catagory.
     */
    private String errorCatagory;

    /**
     * The error level.
     */
    private String errorLevel;

    /**
     * The log to error table.
     */
    private boolean logToErrorTable = true;

    /**
     * Instantiates a new error details.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     */
    public ErrorDetails(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    /**
     * Instantiates a new error details.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @param metadata     the metadata
     */
    public ErrorDetails(String errorCode, String errorMessage, String metadata) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.metadata = metadata;
    }

    /**
     * Instantiates a new error details.
     *
     * @param errorCode     the error code
     * @param errorMessage  the error message
     * @param metadata      the metadata
     * @param errorCatagory the error catagory
     * @param errorLevel    the error level
     */
    public ErrorDetails(String errorCode, String errorMessage, String metadata,
                        String errorCatagory,
                        String errorLevel) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorCatagory = errorCatagory;
        this.errorLevel = errorLevel;
        this.metadata = metadata;
    }

    /**
     * Instantiates a new error details.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @param metadata     the metadata
     * @param errorLevel   the error level
     */
    public ErrorDetails(String errorCode, String errorMessage, String metadata, String errorLevel) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorLevel = errorLevel;
        this.metadata = metadata;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets the error catagory.
     *
     * @return the error catagory
     */
    public String getErrorCatagory() {
        return errorCatagory;
    }

    /**
     * Gets the error level.
     *
     * @return the error level
     */
    public String getErrorLevel() {
        return errorLevel;
    }

    /**
     * Gets the metadata.
     *
     * @return the metadata
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Checks if is log to error table.
     *
     * @return true, if is log to error table
     */
    public boolean isLogToErrorTable() {
        return logToErrorTable;
    }

}
