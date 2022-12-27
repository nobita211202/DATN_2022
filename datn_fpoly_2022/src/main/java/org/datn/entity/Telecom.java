package org.datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TELECOM",
        uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
@Data@AllArgsConstructor@NoArgsConstructor
public class Telecom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telecom_id")
    private Long id;
    private String code;
    @Column(name = "name_")
    private String name;
    @OneToMany(mappedBy = "telecom", fetch = FetchType.LAZY,targetEntity = CardPrice.class)
    @JsonIgnore
    @ToString.Exclude
    private List<Telecom> telecoms;
}
