package com.everis.teacher.app.controllerTest;

import com.everis.teacher.app.model.Teacher;
import com.everis.teacher.app.repository.TeacherRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * .
 * @author lriveras
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class teacherControllerTest {

  /**
   * Injected AppContext.
   */
  @Autowired
  private ApplicationContext applicationContext;

  /**
   * .
   */
  @Autowired
  private TeacherRepository teacherRepository;
  private WebTestClient client;
  private List<Teacher> expectedTeachers;

  /**
   * .
   */
  @BeforeEach
  void setUp() {
    client = WebTestClient
      .bindToApplicationContext(applicationContext)
      .configureClient()
      .baseUrl("/api/v1.0")
      .build();

    Flux<Teacher> initData = teacherRepository.deleteAll()
        .thenMany(Flux.just(
        Teacher.builder().id("1").name("Andres").gender("Masculino").birthdate(new Date())
        .typeID("DNI").numberID("74306050").idCourse("101").build(),
        Teacher.builder().id("2").name("Rodrigo").gender("Masculino").birthdate(new Date())
        .typeID("DNI").numberID("74306051").idCourse("102").build())
        .flatMap(teacherRepository::save))
        .thenMany(teacherRepository.findAll());

    expectedTeachers = initData.collectList().block();
  }

  /**
   * Test FindAll.
   */
  @Test
  void findAll() {

    client.get().uri("/teachers").exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Save.
   */
  @Test
  void save() {
    Teacher expectedTeach = expectedTeachers.get(0);
    client.post().uri("/").body(Mono.just(expectedTeach), Teacher.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test findByID.
   */
  @Test
  void findById() {

    String id = "1";
    client.get().uri("/teachers/{id}", id).exchange()
      .expectStatus().isOk();
  }

  /**
   * Test Update.
   */
  @Test
  void update() {

    Teacher expectedTeach = expectedTeachers.get(0);

    client.put().uri("/teachers/{id}", expectedTeach.getId())
    .body(Mono.just(expectedTeach), Teacher.class).exchange()
      .expectStatus().isCreated();
  }

  /**
   * Test Delete.
   */
  @Test
  void delete() {

    Teacher teachDelete = expectedTeachers.get(0);
    client.delete().uri("/teachers/{id}", teachDelete.getId()).exchange()
      .expectStatus().isOk();
  }

  /**
   * FindByNumberID.
   */
  @Test
  void findByNumberID() {

    String numberID = "74306051";
    client.get().uri("/teachers/doc/{numberID}", numberID).exchange()
      .expectStatus().isFound();
  }

  /**
   * Test FindByName.
   */
  @Test
  void findByName() {

    String name = "Rodrigo";
    client.get().uri("/teachers/nombre/{name}", name).exchange()
      .expectStatus().isFound();
  }

  /**
   * Test FindByBirthDateBetween.
   */
  @Test
  void findBybirthdateBetween() {

    LocalDate birthdate = LocalDate.of(2018,03,02);
    LocalDate birthdate1 = LocalDate.of(2019,03,02);
    client.get().uri("/teachers/date/{birthdate}/{birthdate1}", birthdate,birthdate1).exchange()
        .expectStatus().isOk()
        .expectBodyList(Teacher.class);

  }
}
