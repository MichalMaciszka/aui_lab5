package org.example.config;

import org.example.company.entity.Company;
import org.example.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    private final CompanyService cs;

    @Autowired
    public DataInit(CompanyService cs) {
        this.cs = cs;
    }

    @PostConstruct
    private synchronized void init() {
        Company c1 = Company.builder()
                .name("comp1")
                .budget(41857.45)
                .build();

        Company c2 = Company.builder()
                .name("comp2")
                .budget(68575.65)
                .build();

        cs.create(c1);
        cs.create(c2);
    }
}
