package com.everis.teacherconsumer.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.everis.teacherconsumer.app.service.TeacherConsumerService;

@RestController
public class TeacherConsumerDataController {

  /**
   * Injected TeacherConsumerService.
   */
  TeacherConsumerService teacherService;

  /**
   * .
   */
  @GetMapping(value="/getTeacherInfo")
  public String getTeachers() {
    System.out.println("Making a call to tha Teacher application");
    return teacherService.callTeacherApplication();
  }
}
