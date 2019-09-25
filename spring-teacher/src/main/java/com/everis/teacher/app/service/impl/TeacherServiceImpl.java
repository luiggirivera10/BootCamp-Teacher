package com.everis.teacher.app.service.impl;

import com.everis.teacher.app.model.Teacher;
import com.everis.teacher.app.repository.TeacherRepository;
import com.everis.teacher.app.service.TeacherService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

  /**
 * Injected TeacherRepository.
 */
  @Autowired
  private TeacherRepository teacherRepository;

  /**
 *  Implemet findAll.
 */
  @Override
  public Flux<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  /**
 * Implement findById.
 */
  @Override
  public Mono<Teacher> findById(final String id) {
    return teacherRepository.findById(id);
  }

  /**
   * Implement save.
   */
  @Override
  public Mono<Teacher> save(final Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  /**
   * Implement delete.
   */
  @Override
  public Mono<Void> delete(final Teacher teacher) {
    return teacherRepository.delete(teacher);
  }

  /**
 * Implement findByName.
 */
  @Override
  public Flux<Teacher> findByName(final String name) {
    return teacherRepository.findByName(name);
  }

  /**
   * Implement findByNumberID.
   */
  @Override
  public Mono<Teacher> findByNumberID(final String numberID) {
    return teacherRepository.findByNumberID(numberID);
  }

  /**
   * Implement findByBirthdateBetween.
   */
  @Override
  public Flux<Teacher> findByBirthdateBetween(final Date birthdate, final Date birthdate1) {
    return teacherRepository.findByBirthdateBetween(birthdate, birthdate1);
  }

  /**
   * Implement obtenerPorName.
   */
  @Override
  public Mono<Teacher> obtenerPorName(final String name) {
    return teacherRepository.findName(name);
  }
}
