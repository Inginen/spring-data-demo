package ru.sirius.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sirius.springdatademo.entity.Feedback;

@Repository
public interface ClientRepository extends JpaRepository<Feedback, Long> {
}
