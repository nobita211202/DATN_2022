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
    private List<T> data;
    private int status;
    T value;

    public ResponseData() {
    }

    public ResponseData(String error, String message, List<T> data, int status, T value) {
        this.error = error;
        this.message = message;
        this.data = data;
        this.status = status;
        this.value = value;
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

    public List<T> getData() {
        return null;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
