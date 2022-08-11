package ru.sirius.springdatademo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sirius.springdatademo.entity.Pet;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void savePetTest(){
        // given

        var pet = new Pet();
        pet.setName("Tom");
        // when
        petRepository.save(pet);
        // then
        Assertions.assertNotNull(pet.getId());
    }
}