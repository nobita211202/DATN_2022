package org.datn.dao;

import org.datn.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDAO extends JpaRepository<Token,Long> {
    @Query("select t from Token t where t.tokenCode = ?1")
    Token findByToken(String token);
    @Query("select t from Token t where t.user.id = ?1")
    Token findByUserId(Long id);
}
