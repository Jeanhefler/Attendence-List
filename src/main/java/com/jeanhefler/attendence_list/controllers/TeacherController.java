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

import com.jeanhefler.attendence_list.dtos.TeacherDto;
import com.jeanhefler.attendence_list.services.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll(){
        List<TeacherDto> response = this.teacherService.findTeachers();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id){
        TeacherDto response = this.teacherService.findTeacherById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<TeacherDto> insert(@RequestBody TeacherDto data){
        TeacherDto response = this.teacherService.createTeacher(data);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id, @RequestBody TeacherDto data){
        TeacherDto response = this.teacherService.updateTeacher(id, data);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDto> delete(@PathVariable Long id){
        this.teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
