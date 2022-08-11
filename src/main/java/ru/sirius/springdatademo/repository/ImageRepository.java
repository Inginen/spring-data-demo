package ru.sirius.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sirius.springdatademo.entity.Feedback;
import ru.sirius.springdatademo.entity.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByFeedbackIn(List<Feedback> feedbacks);
}
