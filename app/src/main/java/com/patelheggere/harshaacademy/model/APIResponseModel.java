package com.patelheggere.harshaacademy.model;

public class APIResponseModel {
    boolean status;
    String message;
    String name, phone;
    String id;

    public APIResponseModel() {
    }

    public APIResponseModel(boolean status, String message, String name, String phone, String id) {
        this.status = status;
        this.message = message;
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
