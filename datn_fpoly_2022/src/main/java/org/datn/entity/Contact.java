package org.datn.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "contacts")
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
    private Instant created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactSubject getContactSubject() {
        return contactSubject;
    }

    public void setContactSubject(ContactSubject contactSubject) {
        this.contactSubject = contactSubject;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

}