package org.example.employee.repository;

import org.example.company.entity.Company;
import org.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname);

    void delete(Employee employee);

    @Modifying
    @Query("update Employee e set e.firstname = ?1 where e.id = ?2")
    void setEmployeeFirstname(String firstname, Integer id);

    List<Employee> findAllByCompany(Company company);

    Optional<Employee> findByIdAndCompany(Integer id, Company company);
}
