package org.example.company.dto;


import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCompaniesResponse {

    @Singular
    private List<Company> companies;

    public static Function<Collection<org.example.company.entity.Company>, GetCompaniesResponse> entityToDtoMapper() {
        return companies -> {
            GetCompaniesResponseBuilder response = GetCompaniesResponse.builder();
            companies.stream()
                    .map(company -> Company.builder().name(company.getName())
                            //.budget(company.getBudget())
                            .build())
                    .forEach(response::company);
            return response.build();
        };
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Company {
        private String name;
//        private Double budget;
    }
}
