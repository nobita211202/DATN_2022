package org.datn.entity;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Instant messageTime) {
        this.messageTime = messageTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}