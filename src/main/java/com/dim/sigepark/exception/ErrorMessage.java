package com.dim.sigepark.exception;

//Formato de mensajes de error
public class ErrorMessage {
    
    private String message;
    
    public ErrorMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

}
