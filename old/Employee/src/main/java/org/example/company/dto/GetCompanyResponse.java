package org.example.company.dto;


import lombok.*;
import org.example.company.entity.Company;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetCompanyResponse {
    private String name;
    private Double budget;
    private List<String> names;

    public static Function<Company, GetCompanyResponse> entityToDtoMapper() {
        return company -> GetCompanyResponse.builder()
                .name(company.getName())
//                .budget(company.getBudget())
                .names(company.getEmployees().stream().map(emp -> emp.getFirstname()).collect(Collectors.toList()))
                .build();
    }
}
