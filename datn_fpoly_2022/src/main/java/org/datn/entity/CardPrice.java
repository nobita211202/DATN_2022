package org.datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "card_price")
@Data@AllArgsConstructor@NoArgsConstructor
public class CardPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_price_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id")
    private Telecom telecom;
    private Double price;
    @OneToMany(mappedBy = "cardPrice", fetch = FetchType.LAZY,targetEntity = Card.class)
    @JsonIgnore
    private List<CardPrice> cardPrices;
}
