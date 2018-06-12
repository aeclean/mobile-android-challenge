package com.amaro.bestsellers.domain.networking;

public class NetworkError extends Throwable {

    private String errorMessage;
    private int statusCode;

    public NetworkError(Throwable error) {
        this.errorMessage = error.getMessage();
        this.statusCode = 1;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
