package org.datn.controller;

import org.datn.entity.Cart;
import org.datn.service.CartService;
import org.datn.service.OrderDetailService;
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

    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/get/{idUser}")
    public ResponseEntity getCart(
            @PathVariable("idUser") Long idUser
    ){
        return ResponseEntity.ok(cartService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addCart(
            @RequestBody Cart cart
    ){
        boolean existsOrder = orderDetailService.existsCourseInOrderDetail(cart.getUser().getId(),cart.getCourse().getId());
//        if (orderDetailService.existsCourseInOrderDetail(cart.getUser().getId(),cart.getCourse().getId()));
        if (existsOrder) return ResponseEntity.badRequest().body("Bạn đã mua khóa học này rồi ");
        if(cartService.exists(cart)) return ResponseEntity.badRequest().body("Đã có trong giỏ");
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
