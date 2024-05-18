package com.wjl.ranker;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants Class");
    }

    //Log Constants
    public static final String LOG_PREFIX = "(LOG) ";
    public static final String LOG_ATTEMPTING_TO_SAVE = LOG_PREFIX + "Attempting to Save %s with ID %d: %s";
    public static final String LOG_ATTEMPTING_TO_UPDATE = LOG_PREFIX + "Attempting to Update %s with ID %d: %s";
    public static final String LOG_ATTEMPTING_TO_DELETE = LOG_PREFIX + "Attempting to Delete %s with ID %d";
    public static final String LOG_FAILED_FIND = LOG_PREFIX + "Failed to Find: %s with ID %d";
    public static final String LOG_FAILED_SAVE = LOG_PREFIX + "Failed to Update %s with ID %d: %s";
    public static final String LOG_FAILED_UPDATE = LOG_PREFIX + "Failed to Update %s with ID %d: %s";
    public static final String LOG_FAILED_DELETE = LOG_PREFIX + "Failed to Delete %s with ID %d";
    public static final String LOG_SUCCESSFUL_DELETE = LOG_PREFIX + "%s with ID %d successfully Deleted";

    //Exception Constants
    public static final String EXCEPTION_PREFIX = "(EXCEPTION) ";
    public static final String EXCEPTION_GENERAL_NOT_FOUND = EXCEPTION_PREFIX + "%s not Found";
    public static final String EXCEPTION_GENERAL_FAILED_SAVE = EXCEPTION_PREFIX + "%s failed to Save";
    public static final String EXCEPTION_GENERAL_FAILED_UPDATE = EXCEPTION_PREFIX + "%s failed to Update";
    public static final String EXCEPTION_GENERAL_FAILED_DELETE = EXCEPTION_PREFIX + "%s failed to Delete";

    //Validation Constants
    public static final String VALIDATION_PREFIX = "(INVALID) ";
    public static final String VALIDATION_NOT_NULL = VALIDATION_PREFIX + "Cannot Be Null";
    public static final String VALIDATION_NOT_BLANK = VALIDATION_PREFIX + "Cannot Be Null or Blank";

}
