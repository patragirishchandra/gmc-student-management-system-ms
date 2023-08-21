package com.gmc.studentmanagement.exception.enums;

/**
 * This class is the Enum class for ErrorLevel, providing predefined error levels within an alert system.
 * Each enum value represents a specific error level, such as ERROR, INFO, or WARN.
 * This class offers a standardized way to categorize and communicate error levels in the application.
 *
 * @author Girish chandra Patra
 */
public enum ErrorLevelEnum {

    /**
     * The ERROR error level.
     */
    ERROR("ERROR"),

    /**
     * The INFO error level.
     */
    INFO("INFO"),

    /**
     * The WARN error level.
     */
    WARN("WARN");

    /**
     * The error level.
     */
    private String errorLevel;

    /**
     * Instantiates a new error level enum.
     *
     * @param errorLevel the error level
     */
    ErrorLevelEnum(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    /**
     * Gets the error level.
     *
     * @return the error level
     */
    public String getErrorLevel() {
        return errorLevel;
    }
}

