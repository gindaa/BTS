package com.example.bts;

public class LoginResponse {
    private float statusCode;
    private String message;
    private String errorMessage;
    Data DataObject;

    public LoginResponse(float statusCode, String message, String errorMessage, Data dataObject) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorMessage = errorMessage;
        DataObject = dataObject;
    }


    // Getter Methods

    public float getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods

    public void setStatusCode(float statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }
}
class Data {
    private String token;
    // Getter Methods

    public String getToken() {
        return token;
    }
    // Setter Methods
    public void setToken(String token) {
        this.token = token;
    }

}
