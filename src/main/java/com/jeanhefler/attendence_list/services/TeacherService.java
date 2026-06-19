package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.TeacherDto;
import com.jeanhefler.attendence_list.entities.Teacher;
import com.jeanhefler.attendence_list.mappers.TeacherMapper;
import com.jeanhefler.attendence_list.respositories.TeacherRepository;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherDto> findTeachers(){
        List<TeacherDto> response = this.teacherRepository.findAll().stream()
        .map(teacher -> teacherMapper.toDto(teacher)).toList();
        return response;
    }

    public TeacherDto findTeacherById(Long id){
        Teacher teacher = this.teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
        TeacherDto response = teacherMapper.toDto(teacher);
        return response;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto){
        Teacher data = teacherMapper.toEntity(teacherDto);
        this.teacherRepository.save(data);
        Teacher teacher = this.teacherRepository.findById(data.getId()).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        TeacherDto response = teacherMapper.toDto(teacher);
        return response;
    }

    public TeacherDto updateTeacher(Long id, TeacherDto data){
        Teacher teacher = this.teacherRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        if(!data.name().isEmpty()) teacher.setName(data.name());
        if(!data.address().isEmpty()) teacher.setAddress(data.address());
        if(!data.phone().isEmpty()) teacher.setPhone(data.phone());
        this.teacherRepository.save(teacher);
        TeacherDto response = teacherMapper.toDto(teacher);
        return response;
    }

    public void deleteTeacher(Long id){
        Teacher teacher = this.teacherRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
        this.teacherRepository.delete(teacher);
    }

    public Teacher findEntityById(Long id){
        return this.teacherRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

}
