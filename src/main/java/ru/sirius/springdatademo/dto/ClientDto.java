package ru.sirius.springdatademo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private final Long id;
    private final String firstName;
    private final Long lastName;
}
