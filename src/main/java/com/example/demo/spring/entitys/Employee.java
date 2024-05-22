package com.example.demo.spring.entitys;

import com.example.demo.spring.components.StatusEmployee;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="telephone")
    private Integer telephone;
    @Column(name="status")
    StatusEmployee status = StatusEmployee.ACTIVE;
}
