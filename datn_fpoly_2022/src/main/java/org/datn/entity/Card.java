package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "card",
    uniqueConstraints = @UniqueConstraint(columnNames = {"seri", "code","request_code"})
)
@Data
@NoArgsConstructor@AllArgsConstructor
@ToString
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_price_id")
    private CardPrice cardPrice;
    private String seri;
    private String code;
    @Column(name = "request_code")
    private String requestCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    private Integer status = 1;
    // status_1: chưa duyệt
    // status_2: đã duyệt
}
