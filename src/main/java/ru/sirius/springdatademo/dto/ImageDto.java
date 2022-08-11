package ru.sirius.springdatademo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {
    private Long id;
    private String url;
}
