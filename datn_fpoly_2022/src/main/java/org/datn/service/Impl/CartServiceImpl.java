package org.datn.service.Impl;

import org.datn.dao.CartDao;
import org.datn.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements org.datn.service.CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<Cart> getAll() {
        return cartDao.findAll();
    }

    @Override
    public Optional<Cart> findById(long id) {
        return cartDao.findById(id);
    }

    @Override
    public Cart save(Cart cart ) {
        return cartDao.save(cart);
    }

    @Override
    public void delete(long id) {
        cartDao.deleteById(id);
    }

    @Override
    public Cart put(Cart cart ) {
        return cartDao.save(cart );
    }



    @Override
    public boolean exists(Cart cart) {
        return cartDao.existsByCourseAndUser(cart.getCourse(),cart.getUser());
    }
}
