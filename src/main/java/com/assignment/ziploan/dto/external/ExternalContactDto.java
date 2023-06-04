package com.assignment.ziploan.dto.external;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExternalContactDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
