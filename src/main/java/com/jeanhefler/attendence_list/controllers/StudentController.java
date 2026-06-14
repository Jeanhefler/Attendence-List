package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.attendence_list.dtos.StudentDto;
import com.jeanhefler.attendence_list.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll(){
        List<StudentDto> students = studentService.findStudents();
        return ResponseEntity.ok(students);
    }
}
