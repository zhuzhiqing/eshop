package com.seu.jason.service;

import java.io.Serializable;

/**
 * Created by ToZhu on 2015/11/20.
 */
public class ResponseEntity implements Serializable{
    private String request;

    Object response;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
