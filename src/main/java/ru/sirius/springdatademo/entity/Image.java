package ru.sirius.springdatademo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Table
@Entity(name = "image")
@SequenceGenerator(allocationSize = 1, name = "image_seq", sequenceName = "image_seq")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    private Long id;
    private Instant createdDate;
    private String url;
}
