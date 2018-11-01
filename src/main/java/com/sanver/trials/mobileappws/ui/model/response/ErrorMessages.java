package com.sanver.trials.mobileappws.ui.model.response;

public enum ErrorMessages {
    MISSING_REQUIERED_FIELD("Missing required field. Please check documentation for required fields."),
    RECORD_ALREADY_EXISTS("Record already exists");

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
