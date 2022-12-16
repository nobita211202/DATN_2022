package org.datn.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "categories_attr")
public class CategoriesAttr {
    @Id
    @Column(name = "category_attr_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne( )
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne( )
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Size(max = 255)
    @NotNull
    @Column(name = "image", nullable = false)
    private String image;

    @NotNull
    @Column(name = "name_", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "interest")
    private Long interest;

    @Column(name = "like_")
    private Long like;

    @Column(name = "describe")
    @Type(type = "org.hibernate.type.TextType")
    private String describe;

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


}