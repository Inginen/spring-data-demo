package ru.sirius.springdatademo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Table
@Entity(name = "client")
@SequenceGenerator(allocationSize = 1, name = "client_seq", sequenceName = "client_seq")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;
    private Instant createdDate;
    private String firstName;
    private String lastName;
}
