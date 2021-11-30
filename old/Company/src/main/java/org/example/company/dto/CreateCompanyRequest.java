package org.example.company.dto;


import lombok.*;
import org.example.company.entity.Company;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCompanyRequest {
    private String name;
    private Double budget;

    public static Function<CreateCompanyRequest, Company> dtoToEntityMapper() {
        return request -> Company.builder()
                .name(request.getName())
                .budget(request.getBudget())
                .build();
    }
}
