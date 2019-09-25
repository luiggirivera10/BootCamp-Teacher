package com.everis.teacher.app.repository;

import com.everis.teacher.app.model.Teacher;
import java.util.Date;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
public interface TeacherRepository extends ReactiveMongoRepository<Teacher, String> {

  /**
 * findByName.
 */
  Flux<Teacher> findByName(String name);

  /**
 * findByNumberID.
 */
  Mono<Teacher> findByNumberID(String numberID);

  /**
 * findByBirthdateBetween.
 */
  Flux<Teacher> findByBirthdateBetween(Date birthdate,Date birthdate1);
 
  /**
 * Solo para TEST.
 */
  @Query("{ 'name': ?0 }")
  Mono<Teacher> findName(String name);
}
