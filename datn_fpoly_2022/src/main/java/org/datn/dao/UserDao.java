package org.datn.dao;

import org.datn.entity.BlockUser;
import org.datn.entity.User;
import org.datn.entity.UsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e WHERE e.email LIKE :email and e.status = 0")
    User findByEmail(String email);
    @Query("SELECT u FROM BlockUser u WHERE u.user = :u and u.status =0")
    BlockUser findByEmailInBlockUser(User u);
    @Query("SELECT DISTINCT ar.user FROM UsersRole ar WHERE ar.role.name IN ('DIRE','STAF')")
    List<User> getAdministrators();
    @Modifying
    @Query(value = "update User c set c.password =:pass where c.email =:email")
    void repass(@Param("email") String email, @Param("pass") String pass);
    @Query("SELECT i FROM BlockUser i WHERE i.ipAddress LIKE :i AND i.effectUntil = :d")
    BlockUser findByIPInBlockUser(String i,Instant d);

    @Query("SELECT MAX (i.effectUntil) FROM BlockUser i WHERE i.ipAddress LIKE :ip ")
    Instant findEffectUntilInBlockUser(String ip);

    @Query("SELECT e FROM User e WHERE e.email LIKE :email AND e.password LIKE :password and e.status = 0")
    User findOneByEmailIgnoreCaseAndPassword(String email, String password);

    @Query("select u from User u where (u.username = :username or  u.name like concat('%',:name,'%') and u.status = 0) ")
    List<User> findAllByNameOrUsername(@Param("name") String name,@Param("username") String username);

    @Query("select u from User  u where  u.password = ?1 and u.email = ?2 and u.status = 0")
    User findByUsernameOrEmail(String username, String email);

    @Override
    @Modifying
    @Query("update User u set u.status = 1 where u.id = ?1")
    void deleteById(Long aLong);
}
