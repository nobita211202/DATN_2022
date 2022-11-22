package org.datn.bean;

import org.datn.dao.UserDao;
import org.datn.entity.User;
import org.datn.service.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@Component
public class ResponseData<T> {
    private String error;
    private String message;
    T value;
    private int status;
    private String token;


    public ResponseData() {
    }

    public ResponseData(String error, String message, T value, int status, String token) {
        this.error = error;
        this.message = message;
        this.value = value;
        this.status = status;
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
