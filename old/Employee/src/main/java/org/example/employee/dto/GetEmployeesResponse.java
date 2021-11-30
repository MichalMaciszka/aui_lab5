package org.example.employee.dto;


import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetEmployeesResponse {

    @Singular
    private List<Employee> employees;

    public static Function<Collection<org.example.employee.entity.Employee>, GetEmployeesResponse> entityToDtoMapper() {
        return employees -> {
            GetEmployeesResponseBuilder response = GetEmployeesResponse.builder();
            employees.stream()
                    .map(employee -> Employee.builder()
                            .id(employee.getId())
                            .firstname(employee.getFirstname())
                            .lastname(employee.getLastname())
                            .build())
                    .forEach(response::employee);
            return response.build();
        };
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Employee {
        private Integer id;
        private String firstname;
        private String lastname;
    }
}
