package com.everis.teacher.app.controller;

import com.everis.teacher.app.model.Teacher;
import com.everis.teacher.app.service.TeacherService;
import java.net.URI;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
   * .
   */
  public TeacherRestController() {
    super();
  }

  
}
