package org.datn.service;

import org.datn.entity.Token;

public interface TokenService {
    Token create(Token token);
    Token findByToken(String token);
    Token findByUserId(Long id);
    Token findById(Long id);
    void delete(Long id);


}
