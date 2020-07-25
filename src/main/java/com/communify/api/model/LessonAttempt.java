package com.communify.api.model;

import javax.persistence.Table;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moodle_lesson_attempts")
public class LessonAttempt {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Lesson lesson;
    
    @ManyToOne
    private Student student;
}
