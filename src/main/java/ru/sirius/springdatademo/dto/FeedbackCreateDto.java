package ru.sirius.springdatademo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedbackCreateDto {
    private final String text;
    private final String lastName;
    private final String firstName;
    private final List<String> imageUrls;

}
