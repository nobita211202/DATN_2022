package org.datn.service;

import org.datn.entity.Cours;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    List<Cours> getAllCours();
}
