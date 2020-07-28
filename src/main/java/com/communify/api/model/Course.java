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
    
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<Lesson> lessons;
}
