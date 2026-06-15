package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id){
        StudentDto response = this.studentService.findStudentById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<StudentDto> insert(@RequestBody StudentDto dto){
        StudentDto response = this.studentService.insertNewStudent(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto dto){
        StudentDto response = this.studentService.updateStudent(id, dto);
        return ResponseEntity.ok().body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> delete(@PathVariable Long id){
        StudentDto student = this.studentService.findStudentById(id);
        this.studentService.deleteStudent(student.id());
        return ResponseEntity.noContent().build();
    }
}
