package org.example.company.service;

import javassist.NotFoundException;
import org.example.company.entity.Company;
import org.example.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Optional<Company> find(String id) {
        return companyRepository.findById(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public void create(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public void create(String name, Double budget) {
        Company fresh = Company.builder()
                .name(name)
//                        .budget(budget)
                .build();
        companyRepository.save(fresh);
    }

    @Transactional
    public void updateBudget(Double budget, String name) throws NotFoundException {
        Optional<Company> obj = companyRepository.findByName(name);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Company c = obj.get();
//        c.setBudget(budget);
        companyRepository.save(c);
    }

    @Transactional
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Transactional
    public void update(Company company) {
        companyRepository.save(company);
    }
}
