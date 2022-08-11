package ru.sirius.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sirius.springdatademo.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
