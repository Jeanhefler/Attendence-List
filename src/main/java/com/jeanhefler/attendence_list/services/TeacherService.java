package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.TeacherDto;
import com.jeanhefler.attendence_list.entities.Teacher;
import com.jeanhefler.attendence_list.respositories.TeacherRepository;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherDto toDto(Teacher teacher){
        TeacherDto response = new TeacherDto(
            teacher.getId(),
            teacher.getName(),
            teacher.getPhone(),
            teacher.getAddress());
        return response;
    }

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findTeachers(){
        return this.teacherRepository.findAll();
    }

    public Teacher findTeacherById(Long id){
        return this.teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher createTeacher(Teacher data){
        Teacher teacher = new Teacher();
        if(!data.getName().isEmpty()) teacher.setName(data.getName());
        if(!data.getAddress().isEmpty()) teacher.setAddress(data.getAddress());
        if(!data.getPhone().isEmpty()) teacher.setPhone(data.getPhone());
        this.teacherRepository.save(data);
        return teacher;
    }

    public Teacher updateTeacher(Long id, Teacher data){
        Teacher teacher = this.findTeacherById(id);
        if(!data.getName().isEmpty()) teacher.setName(data.getName());
        if(!data.getAddress().isEmpty()) teacher.setAddress(data.getAddress());
        if(!data.getPhone().isEmpty()) teacher.setPhone(data.getPhone());
        this.teacherRepository.save(teacher);
        return teacher;
    }

    public void deleteTeacher(Long id){
        Teacher teacher = this.findTeacherById(id);
        this.teacherRepository.delete(teacher);
    }

}
