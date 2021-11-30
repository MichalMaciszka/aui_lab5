package org.example.employee.service;

import javassist.NotFoundException;
import org.example.company.entity.Company;
import org.example.company.service.CompanyService;
import org.example.employee.entity.Employee;
import org.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> findByFirstnameAndLastname(String firstname, String lastname) {
        return employeeRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    public Optional<Employee> find(String companyName, Integer id) {
        Optional<Company> company = companyService.find(companyName);
        if (company.isPresent()) {
            return employeeRepository.findByIdAndCompany(id, company.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Employee> findAll(Company company) {
        return employeeRepository.findAllByCompany(company);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void create(String firstname, String lastname, Double salary, Company company) {
        Employee e = Employee.builder()
                .firstname(firstname)
                .lastname(lastname)
                .salary(salary)
                .company(company)
                .build();
        employeeRepository.save(e);
    }

    @Transactional
    public void updateFirstname(String currentFirstname, String lastname, String newFirstname) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findByFirstnameAndLastname(currentFirstname, lastname);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setFirstname(newFirstname);
        employeeRepository.save(e);
    }

    @Transactional
    public void updateFirstname(String newFirstname, Integer id) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findById(id);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setFirstname(newFirstname);
        employeeRepository.save(e);
    }

    @Transactional
    public void updateLastname(String firstname, String currentLastname, String newLastname) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findByFirstnameAndLastname(firstname, currentLastname);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setLastname(newLastname);
        employeeRepository.save(e);
    }

    @Transactional
    public void updateLastname(String newLastname, Integer id) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findById(id);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setLastname(newLastname);
        employeeRepository.save(e);
    }

    @Transactional
    public void updateSalary(String firstname, String lastname, Double salary) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findByFirstnameAndLastname(firstname, lastname);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setSalary(salary);
        employeeRepository.save(e);
    }

    @Transactional
    public void updateSalary(Integer id, Double salary) throws NotFoundException {
        Optional<Employee> obj = employeeRepository.findById(id);
        if (obj.isEmpty()) throw new NotFoundException("Object not found");
        Employee e = obj.get();
        e.setSalary(salary);
        employeeRepository.save(e);
    }

    @Transactional
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }
}
