package com.communify.api.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String classroomEmailAddress;
    private String moodleEmailAddress;
    private String urlPhoto;
}
