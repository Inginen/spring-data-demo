package ru.sirius.springdatademo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Table
@Entity(name = "image")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(allocationSize = 1, name = "image_seq", sequenceName = "image_seq")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    private Long id;
    @CreatedDate
    private Instant createdDate;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;
}
