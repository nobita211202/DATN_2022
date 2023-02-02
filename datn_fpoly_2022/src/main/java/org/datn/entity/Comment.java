package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "comment_")
@Data
@AllArgsConstructor@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name ="numberStar")
    private Short numberStar;

    @Column(name = "commentLine")
    private String commentLine;

    @Column(name = "created")
    private Date created;

    @Column(name = "creator")
    @Type(type = "org.hibernate.type.TextType")
    private String creator;

    @Column(name = "modified")
    private Date modified;

    @Column(name = "modifier")
    @Type(type = "org.hibernate.type.TextType")
    private String modifier;

    @Column(name = "status")
    private Short status;

}