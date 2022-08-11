package ru.sirius.springdatademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sirius.springdatademo.dto.FeedbackCreateDto;
import ru.sirius.springdatademo.dto.FeedbackDto;
import ru.sirius.springdatademo.entity.Client;
import ru.sirius.springdatademo.entity.Feedback;
import ru.sirius.springdatademo.entity.Image;
import ru.sirius.springdatademo.repository.FeedbackRepository;
import ru.sirius.springdatademo.repository.ImageRepository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public Page<FeedbackDto> getByDateOK(Instant date, Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findByDate(date, pageable);
        List<Image> feedbackImages = imageRepository.findByFeedbackIn(feedbacks.getContent());
        return convertFeedbacks(feedbacks, feedbackImages);
    }

    @Transactional(readOnly = true)
    public Page<FeedbackDto> getByDate(Instant date, Pageable pageable) {
        return feedbackRepository.findByDate(date, pageable)
                .map(this::convertFeedback);
    }

    @Transactional
    public Feedback create(FeedbackCreateDto feedbackCreateDto) {
        var client = new Client();
        client.setFirstName(feedbackCreateDto.getFirstName());
        client.setLastName(feedbackCreateDto.getLastName());

        List<Image> images = feedbackCreateDto.getImageUrls()
                .stream()
                .map(this::prepareImage)
                .toList();

        var feedback = new Feedback();
        feedback.setText(feedbackCreateDto.getText());
        feedback.setClient(client);
        images.forEach(feedback::addImage);

        return feedbackRepository.save(feedback);
    }

    private Page<FeedbackDto> convertFeedbacks(Page<Feedback> feedbacks, List<Image> feedbackImages) {
        Map<Long, List<Image>> imagesMap = feedbackImages.stream()
                .collect(Collectors.groupingBy(i -> i.getFeedback().getId()));
        return feedbacks.map(
                feedback -> convertFeedback(feedback, imagesMap.get(feedback.getId()))
        );
    }


    private Image prepareImage(String url) {
        var image = new Image();
        image.setUrl(url);
        return image;
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

    private FeedbackDto convertFeedback(Feedback feedback) {
        List<String> urls = feedback.getImages().stream()
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
