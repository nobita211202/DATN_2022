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
@Table(name = "contacts")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Contact {
    @Id
    @Column(name = "contact_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_subject_id")
    private ContactSubject contactSubject;

    @Column(name = "fullname")
    @Type(type = "org.hibernate.type.TextType")
    private String fullname;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "describe")
    @Type(type = "org.hibernate.type.TextType")
    private String describe;

    @Column(name = "created")
    private Date created;

}