package org.datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryParentChildren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "parent")
    List<Category> children;

    @Column(name = "parent_id")
    private Long parent;

    @Column(name = "name_")
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "interest")
    private Long interest;

    @Column(name = "like_")
    private Long like;

    @Column(name = "created")
    private Date created;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

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