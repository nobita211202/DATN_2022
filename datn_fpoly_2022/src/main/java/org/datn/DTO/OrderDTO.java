package org.datn.DTO;

import org.datn.entity.OrderDetail;

import java.util.List;

public class OrderDTO {
    private String email;

    private List<OrderDetail> items;

    public OrderDTO() {
    }

    public OrderDTO(String email, List<OrderDetail> items) {
        this.email = email;

        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<OrderDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }
}
