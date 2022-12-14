package org.datn.service;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> getAllOrder();
    ResponseEntity<?> getOrderById(Long id);
    ResponseEntity<?> paymentSuccessAndUpdateStatus(Long id);

}
