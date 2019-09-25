package com.everis.teacher.app.service;

import com.everis.teacher.app.model.Teacher;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
public interface TeacherService {
  /**
 * Metodo FindAll.
 */
  Flux<Teacher> findAll();

  /**
 * Metodo FindById.
 */
  Mono<Teacher> findById(String id);

  /**
 * Metodo save.
 */
  Mono<Teacher> save(Teacher teacher);

  /**
 * Metodo delete.
 */
  Mono<Void> delete(Teacher teacher);

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
  Flux<Teacher> findByBirthdateBetween(Date birthdate, Date birthdate1);

  /**
 * Solo para TEST.
 */
  Mono<Teacher> obtenerPorName(String name);
}
