package com.everis.teacher.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * .
 * @author lriveras
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "teachers")
public class Teacher {
  /**
   * .
   */
  @Id
 private String id;
  /**
   * .
   */
  @NotEmpty(message = "'name' No debe ser vacio!")
 private String name;
  /**
 * .
 */
  @NotEmpty(message = "'gender' No debe ser vacio!")
 private String gender;
  /**
   * .
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
 private Date birthdate;
  /**
   * .
   */
  @NotEmpty(message = "'typeID' No debe ser vacio!")
 private String typeID;
  /**
   * .
   */
  @NotEmpty(message = "'numberID' No debe ser vacio!")
  @Size(min = 8, max = 8,message = "'numberID' debe tener 8 caracteres")
 private String numberID;
  /**
 * iidFamily.
 */
  @NotEmpty(message = "'idCourse' No debe ser vacio!")
 private String idCourse;  
  /**
   * .
   */
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date createdAt = new Date();
  /**
   * .
   */
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
 private Date modifiedAt = new Date();

}
