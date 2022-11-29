package org.datn.DTO;

import java.time.Instant;

public class ResponseOrderDTO {
    private float price;
    private Instant date;
    private Long orderId;
    
    private String code;

    public ResponseOrderDTO(float price, Instant date, Long orderId, String code) {
        this.price = price;
        this.date = date;
        this.orderId = orderId;
        this.code = code;
    }




    public ResponseOrderDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
