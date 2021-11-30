package org.example.employee.entity;


import lombok.*;
import org.example.company.entity.Company;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private double salary;

    @ManyToOne
    private Company company;
}
