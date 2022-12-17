package org.datn.service;

import org.datn.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> getAll();
    Optional<Cart> findById(long id);
    Cart save(Cart admin);
    void delete(long id);
    Cart put(Cart admin);
    boolean exists(Cart cart);
}
