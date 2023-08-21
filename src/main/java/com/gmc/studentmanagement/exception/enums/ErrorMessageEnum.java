package com.gmc.studentmanagement.exception.enums;

/**
 * This class is the Enum class for Error Messages, containing predefined error message strings.
 * Each enum value represents a specific error message that can be associated with an error condition.
 * This class provides a standardized way to handle and communicate error messages in the application.
 *
 * @since 2023
 * @author patragirishchandra@gmail.com
 */
public enum ErrorMessageEnum {


    AGE_RESTRICTION_MSG("Age should be between 15 and 20 years."),

    RECORD_NOT_FOUND_MSG("Record not found for studentId: "),

    INVALID_REQUEST_MSG("Invalid Request"),

    SOMETHING_WENT_WRONG("Something went wrong.");

    /**
     * The error message.
     */
    private final String errorMessage;

    /**
     * Instantiates a new error message enum.
     *
     * @param errorMessage the error message
     */
    ErrorMessageEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}

