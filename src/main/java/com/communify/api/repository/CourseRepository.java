package com.communify.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communify.api.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
