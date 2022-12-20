package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "voucher_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherType {
    @Id
    @Column(name = "voucher_type_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name_", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "created")
    private Instant created;

    @Column(name = "creator")
    @Type(type = "org.hibernate.type.TextType")
    private String creator;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "modifier")
    @Type(type = "org.hibernate.type.TextType")
    private String modifier;
}