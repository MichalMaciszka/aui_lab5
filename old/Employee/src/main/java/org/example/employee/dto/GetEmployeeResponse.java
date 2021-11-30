package org.example.employee.dto;

import lombok.*;
import org.example.employee.entity.Employee;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetEmployeeResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private Double salary;
    private String company;

    public static Function<Employee, GetEmployeeResponse> entityToDtoMapper() {
        return employee -> GetEmployeeResponse.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .salary(employee.getSalary())
                .company(employee.getCompany().getName())
                .build();
    }
}
