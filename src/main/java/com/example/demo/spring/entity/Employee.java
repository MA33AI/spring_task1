package com.example.demo.spring.entity;

import com.example.demo.spring.entity.enumeration.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String address;
    private Integer telephone;
    @Enumerated
    private StatusEmployee status;
}
