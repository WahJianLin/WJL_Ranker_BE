package com.wjl.ranker.exceptions;

public class GeneralException extends RuntimeException {

    public GeneralException(String errorMsg) {
        super("Error: " + errorMsg);
    }

}
