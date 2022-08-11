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
@SequenceGenerator(allocationSize = 1, name = "feedback_seq", sequenceName = "feedback_seq")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
    private Long id;
    private Instant createdDate;
    private String text;
}
