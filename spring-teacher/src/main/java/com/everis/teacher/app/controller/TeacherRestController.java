package com.everis.teacher.app.controller;

import com.everis.teacher.app.model.Teacher;
import com.everis.teacher.app.service.TeacherService;
import java.net.URI;
import java.util.Date;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@RestController
@RefreshScope
@RequestMapping("/api/v1.0")
public class TeacherRestController {

  /**
   * Injected LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(TeacherRestController.class);

  /**
 * Injected service.
 */
  @Autowired
  private TeacherService service;

  /**
   * . Service listar Teacher
   */
  @GetMapping("/teachers")
  public Mono<ResponseEntity<Flux<Teacher>>> findAll() {
    return Mono.just(

        ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
  }
  /**

   * Servicio buscar por ID.

   */
  @GetMapping("/teachers/{id}")
 public Mono<Teacher> findById(@PathVariable final String id) {
    final Flux<Teacher> teachers = service.findAll();
    final Mono<Teacher> teacher = teachers.filter(s -> s.getId().equals(id))
                        .next().doOnNext(teach -> LOG.info(teach.getName()));
    return teacher;
  }

  /**

   * Servicio para buscar por nombre devuelve una lista.

   */
  @GetMapping("/teachers/name/{name}")
  public Flux<Teacher> findByName(@PathVariable ("name") final String name) {
    return service.findByName(name)
        .doOnNext(teach -> LOG.info(teach.getName()));
  }

  /**

   * Servicio para buscar por nombre devuelve una lista.

   */
  @GetMapping("/teachers/namecourse/{nameCourse}")
  public Flux<Teacher> findByNameCourse(@PathVariable ("nameCourse") final String nameCourse) {
    return service.findByNameCourse(nameCourse)
        .doOnNext(teach -> LOG.info("Curso de Docente:  "+teach.getName()+" - "+teach.getNameCourse()));
  }

  /**
 * .
 */
  @GetMapping("/teachers/nombre/{name}")
  public Mono<ResponseEntity<Teacher>> getByName(@PathVariable ("name") final String name) {
    return service.obtenerPorName(name).doOnNext(teach -> LOG.info(teach.getName()))
              .map(teacher -> new ResponseEntity<>(teacher, HttpStatus.FOUND))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**

   * Servicio para buscar por DNI.

   */
  @GetMapping("/teachers/doc/{numberID}")
  public Mono<ResponseEntity<Teacher>> 
      findByNumberID(@PathVariable ("numberID") final String numberID) {
    return service.findByNumberID(numberID)
              .map(teacher -> new ResponseEntity<>(teacher, HttpStatus.FOUND))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**
   * . Service save
   */

  @PostMapping
  public Mono<ResponseEntity<Teacher>> save(@RequestBody Teacher teacher) {
    if (teacher.getBirthdate() == null) {
      teacher.setBirthdate(new Date());
    }
    return service.save(teacher)
        .map(p -> ResponseEntity.created(URI.create("/api/v1.0/teachers/".concat(p.getId())))
            .contentType(MediaType.APPLICATION_JSON_UTF8).body(p));

  }
  /**

   * Servicio para modificar.

   */
  @PutMapping("/teachers/{id}")
    public Mono<ResponseEntity<Teacher>> updateTeacher(@PathVariable(value = "id") final String id,
                                                   @Valid @RequestBody final Teacher teacher) {
    return service.findById(id)
                .flatMap(existingTeacher -> {
                  existingTeacher.setName(teacher.getName());
                  existingTeacher.setGender(teacher.getGender());
                  existingTeacher.setBirthdate(teacher.getBirthdate());
                  existingTeacher.setTypeID(teacher.getTypeID());
                  existingTeacher.setNumberID(teacher.getNumberID());
                  existingTeacher.setNameCourse(teacher.getNameCourse());
                  existingTeacher.setModifiedAt(teacher.getModifiedAt());
                  return service.save(existingTeacher);
                })
                .map(updateTeacher -> new ResponseEntity<>(updateTeacher, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**

   * Servicio para eliminar.

   */
  @DeleteMapping("/teachers/{id}")
  public Mono<ResponseEntity<Void>> deleteTeacher(@PathVariable(value = "id") final String id) {
    return service.findById(id)
    .flatMap(existingTeacher ->
 service.delete(existingTeacher)
 .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
 .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**

   * Servicion para buscar entre fechas.

   */
  @GetMapping("/teachers/date/{birthdate}/{birthdate1}")
  public Flux<Teacher> findByBirthdate(@PathVariable("birthdate")
      @DateTimeFormat(iso = ISO.DATE) final Date birthdate,@PathVariable("birthdate1")
      @DateTimeFormat(iso = ISO.DATE) final Date birthdate1) {
    return service.findByBirthdateBetween(birthdate, birthdate1);
  }

  /**
   * .
   */
  public TeacherRestController() {
    super();
  }

  
}
