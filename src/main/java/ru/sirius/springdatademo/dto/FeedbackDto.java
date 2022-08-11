package ru.sirius.springdatademo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class FeedbackDto {
    private final Long id;
    private final Instant createdDate;
    private final String text;
    private final ClientDto client;
    private final List<String> imageUrls;

}
