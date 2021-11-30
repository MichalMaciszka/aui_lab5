package org.example.employee.dto;

import lombok.*;
import org.example.employee.entity.Employee;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateEmployeeRequest {
    private String firstname;
    private String lastname;
    private Double salary;

    public static BiFunction<Employee, UpdateEmployeeRequest, Employee> dtoToEntityUpdater() {
        return (employee, request) -> {
            employee.setFirstname(request.getFirstname());
            employee.setLastname(request.getLastname());
            employee.setSalary(request.getSalary());
            return employee;
        };
    }
}
