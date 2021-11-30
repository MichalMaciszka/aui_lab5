package org.example.company.event.dto;


import lombok.*;
import org.example.company.entity.Company;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateCompanyRequest {
    private String name;
    private double budget;

    public static Function<Company, CreateCompanyRequest> entityToDtoMapper() {
        return e -> CreateCompanyRequest.builder()
                .name(e.getName())
                .budget(e.getBudget())
                .build();
    }
}
