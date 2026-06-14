package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.StudentDto;
import com.jeanhefler.attendence_list.entities.Student;
import com.jeanhefler.attendence_list.mappers.StudentMapper;
import com.jeanhefler.attendence_list.respositories.StudentRepository;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> findStudents(){
        List<StudentDto> students = this.studentRepository.findAll()
        .stream().map(student -> studentMapper.toDto(student)
        ).toList();
        return students;
    }

    public StudentDto findStudentById(Long id){
        Student data = this.studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        StudentDto response = studentMapper.toDto(data);
        return response;
    }

}
