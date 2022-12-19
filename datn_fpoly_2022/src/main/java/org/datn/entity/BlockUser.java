package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "block_user")
@AllArgsConstructor
@Data
@NoArgsConstructor
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

}