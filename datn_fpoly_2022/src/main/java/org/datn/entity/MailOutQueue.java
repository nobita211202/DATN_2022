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
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "mail_out_queue")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailOutQueue {
    @Id
    @Column(name = "message_id", nullable = false)
    private Long id;

    @Column(name = "message_time")
    private Instant messageTime;

    @Column(name = "sender")
    @Type(type = "org.hibernate.type.TextType")
    private String sender;

    @Size(max = 500)
    @Column(name = "recipient", length = 500)
    private String recipient;

    @NotNull
    @Column(name = "subject", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String subject;

    @NotNull
    @Column(name = "content_", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String content;

}