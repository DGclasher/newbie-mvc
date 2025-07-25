package io.springguru.springrestmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private String customerName;
    private Integer version;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
