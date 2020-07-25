package com.communify.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communify.api.model.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

}
