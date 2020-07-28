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
    
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "Student_Course", 
        joinColumns = { @JoinColumn(name = "student_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Student> students;
    
    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<Lesson> lessons;
}
