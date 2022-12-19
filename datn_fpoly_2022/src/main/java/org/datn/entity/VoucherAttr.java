package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "voucher_attr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherAttr {
    @Id
    @Column(name = "voucher_attr_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "status")
    private Short status;


}