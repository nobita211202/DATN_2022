package org.datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "user")
    private List<UsersRole> usersRoles;


    @Column(name = "name_")
    private String name;

    @Size(max = 255)
    @Column(name = "username", nullable = false)
    private String username;

    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    @Type(type = "org.hibernate.type.TextType")
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Size(max = 255)
    @Column(name = "token")
    private String token;

    @Column(name = "money")
    private Float money;

    @Column(name = "created")
    private Date created;

    @Column(name = "creator")
    @Type(type = "org.hibernate.type.TextType")
    private String creator;

    @Column(name = "modified")
    private Date modified;

    @Column(name = "modifier")
    @Type(type = "org.hibernate.type.TextType")
    private String modifier;

    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,targetEntity = Card.class)
    @JsonIgnore
    @ToString.Exclude
    private List<User> users;
}