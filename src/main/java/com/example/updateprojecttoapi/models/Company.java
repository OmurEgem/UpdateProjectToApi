package com.example.updateprojecttoapi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    public String locatedCountry;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL)
    public List<Course> course;

}
