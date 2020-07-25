package com.communify.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communify.api.model.LessonAttempt;

@Repository
public interface LessonAttemptRepository extends CrudRepository<LessonAttempt, Long> {

}
