package org.example.config;

import org.example.company.entity.Company;
import org.example.company.service.CompanyService;
import org.example.employee.entity.Employee;
import org.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public DataInit(EmployeeService es, CompanyService cs) {
        employeeService = es;
        companyService = cs;
    }

    @PostConstruct
    private synchronized void init() {

        Company c1 = Company.builder()
                .name("comp1")
//                .budget(41857.45)
                .build();

        Company c2 = Company.builder()
                .name("comp2")
//                .budget(68575.65)
                .build();

        companyService.create(c1);
        companyService.create(c2);

        Employee e1 = Employee.builder()
                .firstname("C")
                .lastname("E1")
                .salary(5279.22)
                .company(c1)
                .build();

        Employee e2 = Employee.builder()
                .firstname("B")
                .lastname("E2")
                .salary(3458.54)
                .company(c1)
                .build();

        Employee e3 = Employee.builder()
                .firstname("A")
                .lastname("E3")
                .salary(1586.45)
                .company(c2)
                .build();

        employeeService.create(e1);
        employeeService.create(e2);
        employeeService.create(e3);
    }

}
