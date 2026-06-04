package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.TeacherDto;
import com.jeanhefler.attendence_list.entities.Teacher;
import com.jeanhefler.attendence_list.respositories.TeacherRepository;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDto> findTeachers(){
        List<TeacherDto> response = this.teacherRepository.findAll().stream()
        .map(teacher -> new TeacherDto(
            teacher.getId(), 
            teacher.getName(), 
            teacher.getPhone(), 
            teacher.getAddress())).toList();
        return response;
    }

    public TeacherDto findTeacherById(Long id){
        Teacher teacher = this.teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
        TeacherDto response = new TeacherDto(
            teacher.getId(), 
            teacher.getName(), 
            teacher.getPhone(),
            teacher.getAddress());
            return response;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto){
        Teacher data = new Teacher(
            teacherDto.id(),
            teacherDto.name(), 
            teacherDto.phone(), 
            teacherDto.address());
        this.teacherRepository.save(data);
        Teacher teacher = this.teacherRepository.findById(data.getId()).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        TeacherDto response = new TeacherDto(
            teacher.getId(),
            teacher.getName(),
            teacher.getPhone(),
            teacher.getAddress()
        );
        return response;
    }

    public TeacherDto updateTeacher(Long id, TeacherDto data){
        Teacher teacher = this.teacherRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        if(!data.name().isEmpty()) teacher.setName(data.name());
        if(!data.address().isEmpty()) teacher.setAddress(data.address());
        if(!data.phone().isEmpty()) teacher.setPhone(data.phone());
        this.teacherRepository.save(teacher);
        TeacherDto response = new TeacherDto(
            teacher.getId(),
            teacher.getName(),
            teacher.getPhone(),
            teacher.getAddress()
        );
        return response;
    }

    public void deleteTeacher(Long id){
        Teacher teacher = this.teacherRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        this.teacherRepository.delete(teacher);
    }

}
