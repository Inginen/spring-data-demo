package ru.sirius.springdatademo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity(name = "feedback")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(allocationSize = 1, name = "feedback_seq", sequenceName = "feedback_seq")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
    private Long id;
    @CreatedDate
    private Instant createdDate;
    @Column(name = "text")
    private String text;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "feedback")
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        this.images.add(image);
        image.setFeedback(this);
    }
}
