package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "contact_subject")
@Data
@AllArgsConstructor@NoArgsConstructor
public class ContactSubject {
    @Id
    @Column(name = "contact_subject_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_")
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "code", nullable = false)
    private String code;


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