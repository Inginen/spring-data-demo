package ru.sirius.springdatademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sirius.springdatademo.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
