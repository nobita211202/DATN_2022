package org.datn.controller;

import org.datn.entity.Cart;
import org.datn.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping ("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/get")
    public ResponseEntity getCart(){
        return ResponseEntity.ok(cartService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addCart(
            @RequestBody Cart cart
    ){
        if(cartService.exists(cart)) return ResponseEntity.badRequest().build();
        else {
            cartService.save(cart);
            return ResponseEntity.ok(cart);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable("id") Long id
    ){
        cartService.delete(id);
    }


}
