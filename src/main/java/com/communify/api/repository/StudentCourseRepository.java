package com.communify.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communify.api.model.StudentCourse;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourse, Long> {

}
