package org.datn.service.Impl;

import org.datn.dao.TokenDAO;
import org.datn.entity.Token;
import org.datn.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenDAO tokenDAO;

    @Override
    public Token create(Token token) {
        return tokenDAO.save(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenDAO.findByToken(token);
    }

    @Override
    public Token findByUserId(Long id) {
        return tokenDAO.findByUserId(id);
    }

    @Override
    public Token findById(Long id) {
        return tokenDAO.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        tokenDAO.deleteById(id);
    }
}
