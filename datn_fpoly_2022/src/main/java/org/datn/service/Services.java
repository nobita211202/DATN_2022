package org.datn.service;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collection;

public interface Services<T extends Serializable> {
    ResponseEntity<?> save(T entity);
    ResponseEntity<?> update(T entity);
    ResponseEntity<?> delete(T entity);
    ResponseEntity<?> deleteById(Long id);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<Collection<?>> findAll();
}
