package ru.sirius.springdatademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sirius.springdatademo.dto.FeedbackCreateDto;
import ru.sirius.springdatademo.dto.FeedbackDto;
import ru.sirius.springdatademo.entity.Feedback;
import ru.sirius.springdatademo.entity.Image;
import ru.sirius.springdatademo.repository.FeedbackRepository;
import ru.sirius.springdatademo.repository.ImageRepository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ImageRepository imageRepository;

    public List<FeedbackDto> getByDate(Instant date) {
        return Collections.emptyList();
    }

    @Transactional
    public Feedback create(FeedbackCreateDto feedbackCreateDto) {
        return new Feedback();
    }

    private FeedbackDto convertFeedback(Feedback feedback, List<Image> images) {
        List<String> urls = images.stream()
                .map(Image::getUrl)
                .toList();
        return FeedbackDto.builder()
                .id(feedback.getId())
                .createdDate(feedback.getCreatedDate())
                .text(feedback.getText())
                .imageUrls(urls)
                .build();
    }
}
