package ru.sirius.springdatademo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sirius.springdatademo.entity.Feedback;

import java.time.Instant;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @EntityGraph(attributePaths = {"client"})
    @Query("select f from feedback f where f.createdDate < :date")
    Page<Feedback> findByDate(@Param("date") Instant date, Pageable pageable);
}
