package com.security.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.security.demo.model.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class StudentController {

  private List<Student> students = new ArrayList<>(List.of(
    new Student(1, "Harsh", 100),
    new Student(2, "Pravin", 200),
    new Student(3, "Navin", 300)
  )
  );

  @GetMapping("/students")
  public List<Student> getStudents() {
      return students;
  }

  
}