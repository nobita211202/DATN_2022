package org.datn.service;

import org.datn.entity.Cart;
import org.datn.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> getAll();
    List<Cart> getCartByUser(Long idUser);
    Optional<Cart> findById(long id);
    Cart save(Cart admin);
    void delete(long id);
    Cart put(Cart admin);
    boolean exists(Cart cart);
}
