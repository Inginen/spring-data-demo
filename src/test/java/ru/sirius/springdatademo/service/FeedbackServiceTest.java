package ru.sirius.springdatademo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sirius.springdatademo.dto.FeedbackCreateDto;
import ru.sirius.springdatademo.dto.FeedbackDto;
import ru.sirius.springdatademo.entity.Feedback;
import ru.sirius.springdatademo.repository.FeedbackRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FeedbackServiceTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackService feedbackService;

    @Test
    void createFeedbackTest() {
        // given
        var feedbackDto = prepareFeedback("Good", "John", "Doe",
                List.of(prepareImage(), prepareImage()));
        // when
        Feedback feedback = feedbackService.create(feedbackDto);
        // then
        assertNotNull(feedback.getId());
    }

    @Test
    void getFeedbacksTest() {
        // given
        feedbackService.create(prepareFeedback("Good", "John", "Smith", List.of(prepareImage(), prepareImage())));
        feedbackService.create(prepareFeedback("Ok", "John", "Doe", List.of(prepareImage(), prepareImage())));
        feedbackService.create(prepareFeedback("Bad", "John", "Travolta", List.of(prepareImage(), prepareImage())));
        feedbackService.create(prepareFeedback("Terrible", "John", "Snow", List.of(prepareImage(), prepareImage())));
        // when
        List<FeedbackDto> feedbacks = feedbackService.getByDate(Instant.now().plus(1, ChronoUnit.DAYS));
        // then
        assertFalse(feedbacks.isEmpty());
    }


    private static FeedbackCreateDto prepareFeedback(String text, String firstName, String lastName, List<String> images) {
        return FeedbackCreateDto.builder()
                .text(text)
                .firstName(firstName)
                .lastName(lastName)
                .imageUrls(images)
                .build();

    }

    private static String prepareImage() {
        return "Image ???" + new Random(100).nextInt(100);
    }
}