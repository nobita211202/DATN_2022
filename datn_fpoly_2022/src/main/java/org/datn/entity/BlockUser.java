package org.datn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "block_user")
public class BlockUser {
    @Id
    @Column(name = "block_user_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 255)
    @NotNull
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "created")
    private Instant created;

    @Column(name = "effect_from")
    private Instant effectFrom;

    @Column(name = "effect_until")
    private Instant effectUntil;

    @Column(name = "status")
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getEffectFrom() {
        return effectFrom;
    }

    public void setEffectFrom(Instant effectFrom) {
        this.effectFrom = effectFrom;
    }

    public Instant getEffectUntil() {
        return effectUntil;
    }

    public void setEffectUntil(Instant effectUntil) {
        this.effectUntil = effectUntil;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}