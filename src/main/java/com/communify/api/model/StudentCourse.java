package com.communify.api.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moodle_student_course")
public class StudentCourse {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Student student;
    
    @ManyToOne
    private Course course;
}
