package org.datn.service;

import org.datn.entity.Cours;
import org.datn.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    List<Cours> getAllCours();

    OrderDetail findByOrderID(Long id);
}
