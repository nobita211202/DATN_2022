package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "login_fails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginFail {
    @Id
    @Column(name = "login_fails_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Size(max = 255)
    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "created")
    private Instant created;

    @Size(max = 255)
    @Column(name = "os_name")
    private String osName;

    @Column(name = "location_")
    @Type(type = "org.hibernate.type.TextType")
    private String location;

}