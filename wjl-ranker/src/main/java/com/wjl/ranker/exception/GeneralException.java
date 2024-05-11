package com.wjl.ranker.exception;

public class GeneralException extends RuntimeException {

    public GeneralException(String errorMsg) {
        super("Error: " + errorMsg);
    }

}
