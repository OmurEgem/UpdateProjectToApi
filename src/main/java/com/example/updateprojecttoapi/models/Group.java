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
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> course;

    @OneToMany(mappedBy = "groups"
            , cascade = CascadeType.ALL)
    private List<Student> student;
@ManyToOne
    private Company company;

}
