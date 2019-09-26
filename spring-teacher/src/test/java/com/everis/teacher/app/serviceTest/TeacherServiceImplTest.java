package com.everis.teacher.app.serviceTest;

import static org.mockito.Mockito.when;

import com.everis.teacher.app.model.Teacher;
import com.everis.teacher.app.repository.TeacherRepository;
import com.everis.teacher.app.service.impl.TeacherServiceImpl;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * .
 * @author lriveras
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TeacherServiceImplTest {

  /**
   * Inject StudentRepository.
   */
  @Mock
  private TeacherRepository teacherRepository;

  /**
   * Inject StudentServiceImpl.
   */
  @InjectMocks
  private TeacherServiceImpl teacherService;

  /**
   * finaAll.
   */
  @Test
  public void findAll_ServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setName("Walter");
    teacher.setGender("Masculino");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("DNI");
    teacher.setNumberID("55556666");
    teacher.setIdCourse("12312312");
    
    when(teacherService.findAll()).thenReturn(Flux.just(teacher));
    final Flux<Teacher> actua = teacherService.findAll();
    assertResults(actua, teacher);
  }

  /**
   * findById-Exist.
   */
  @Test
  public void findById_exist_ServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("102");
    teacher.setName("Ramon");
    teacher.setGender("Masculino");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("DNI");
    teacher.setNumberID("10210210");
    teacher.setIdCourse("12312312");
    when(teacherRepository.findById(teacher.getId())).thenReturn(Mono.just(teacher));
    final Mono<Teacher> actual = teacherRepository.findById(teacher.getId());
    assertResults(actual, teacher);
  }

  /**
   * findById-not-Exist.
   */
  @Test
  public void findById_not_exist_ServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("101");
    teacher.setName("Tito");
    teacher.setGender("Masculino");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("DNI");
    teacher.setNumberID("10110110");
    when(teacherRepository.findById(teacher.getId())).thenReturn(Mono.empty());
    final Mono<Teacher> actual = teacherRepository.findById(teacher.getId());
    assertResults(actual);
  }

  /**
   * save.
   */
  @Test
  public void saveServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("10");
    teacher.setName("Victor");
    teacher.setGender("Masculino");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("DNI");
    teacher.setNumberID("44443333");
    teacher.setIdCourse("12312312");
    teacher.setCreatedAt(new Date());
    teacher.setModifiedAt(new Date());
    when(teacherRepository.save(teacher)).thenReturn(Mono.just(teacher));
    final Mono<Teacher> actual = teacherService.save(teacher);
    assertResults(actual, teacher);
  }
  
  /**
   * deleteTest.
   */
  @Test
  public void deleteServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("10");
    teacher.setName("Victor");
    teacher.setGender("Masculino");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("DNI");
    teacher.setNumberID("44443333");
    teacher.setIdCourse("12312312");
    teacher.setCreatedAt(new Date());
    teacher.setModifiedAt(new Date());
    
    when(teacherRepository.delete(teacher)).thenReturn(Mono.empty());
  }

  /**
   * findByNumberID.
   */
  @Test
  public void findBynNumberID_ServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("dekweowe");
    teacher.setName("cristohper");
    teacher.setGender("male");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("dni");
    teacher.setNumberID("736723727");
    teacher.setIdCourse("12312312");
    final String numberID = "736723727";
    when(teacherRepository.findByNumberID(numberID)).thenReturn(Mono.just(teacher));
    final Mono<Teacher> actual = teacherService.findByNumberID(numberID);
    assertResults(actual,teacher);
  }

  /**
   * findByName.
   */
  @Test
  public void findByName_ServiceImplTest() {
    final Teacher teacher = new Teacher();
    teacher.setId("dekweowe");
    teacher.setName("cristohper");
    teacher.setGender("male");
    teacher.setBirthdate(new Date());
    teacher.setTypeID("dni");
    teacher.setNumberID("736723727");
    teacher.setIdCourse("12312312");
    final String name = "736723727";
    when(teacherRepository.findByName(name)).thenReturn(Flux.just(teacher));
    final Flux<Teacher> actual = teacherService.findByName(name);
    assertResults(actual,teacher);
  }

  /**
   * assertResults.
   */
  private void assertResults(Publisher<Teacher> publisher, Teacher... expectedTeachers) {
    StepVerifier
        .create(publisher)
        .expectNext(expectedTeachers)
        .verifyComplete();
  }

}
