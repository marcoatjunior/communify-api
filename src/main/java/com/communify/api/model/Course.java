package com.communify.api.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moodle_course")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String fullname;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<StudentCourse> studentCourses;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Lesson> lessons;
}
