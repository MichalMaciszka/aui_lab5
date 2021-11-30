package org.example.company.event.repository;

import org.example.company.entity.Company;
import org.example.company.event.dto.CreateCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CompanyEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public CompanyEventRepository(@Value("${example.employees.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Company company) {
        restTemplate.delete("/companies/{company}", company.getName());
    }

    public void create(Company company) {
        restTemplate.postForLocation("/companies", CreateCompanyRequest.entityToDtoMapper().apply(company));
    }

}
