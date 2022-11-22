package org.datn.dao;

import org.datn.entity.BlockUser;
import org.datn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.Instant;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e WHERE e.email LIKE :email")
    User findByEmail(String email);
    @Query("SELECT u FROM BlockUser u WHERE u.user = :u")
    BlockUser findByEmailInBlockUser(User u);
    @Query("SELECT DISTINCT ar.user FROM UsersRole ar WHERE ar.role.name IN ('DIRE','STAF')")
    List<User> getAdministrators();

    @Query("SELECT i FROM BlockUser i WHERE i.ipAddress LIKE :i AND i.effectUntil = :d")
    BlockUser findByIPInBlockUser(String i,Instant d);

    @Query("SELECT MAX (i.effectUntil) FROM BlockUser i WHERE i.ipAddress LIKE :ip ")
    Instant findEffectUntilInBlockUser(String ip);

    @Query("SELECT e FROM User e WHERE e.email LIKE :email AND e.password LIKE :password")
    User findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
