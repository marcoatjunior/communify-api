package com.communify.api.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moodle_lesson")
public class Lesson {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long deadline;
    private String activityLink;
    
    @ManyToOne
    private Course course;
}
