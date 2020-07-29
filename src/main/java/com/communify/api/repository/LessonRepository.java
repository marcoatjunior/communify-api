package com.communify.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.communify.api.entity.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l "
        + "join fetch l.course c "
        + "join fetch c.students s "
        + "where s.email = :email")
    List<Lesson> findByEmail(String email);
}
