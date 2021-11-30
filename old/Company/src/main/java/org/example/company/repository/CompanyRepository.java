package org.example.company.repository;

import org.example.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    List<Company> findAll();

    Optional<Company> findByName(String name);
}
