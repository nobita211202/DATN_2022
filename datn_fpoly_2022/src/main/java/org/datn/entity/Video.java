package org.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id", nullable = false)
    private Long id;

    @Column(name = "free")
    private boolean free;

    @Column(name = "describe")
    private String user;
    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "status")
    private Short status;

    @Column(name = "created")
    private Date created;
    @Column(name = "creator")
    private String creator;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modified")
    private Date modified;
    // status_1: chưa duyệt
    // status_2: đã duyệt
}
