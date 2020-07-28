package com.communify.api.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moodle_student")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}
