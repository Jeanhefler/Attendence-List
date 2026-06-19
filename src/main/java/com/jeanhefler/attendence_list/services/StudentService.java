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
    private ClassRoomService classRoomService;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
        ClassRoomService classRoomService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classRoomService = classRoomService;
    }

    public List<StudentDto> findStudents(){
        return this.studentRepository.findAll()
        .stream().map(student -> studentMapper.toDto(student)
        ).toList();
    }

    public StudentDto findStudentById(Long id){
        Student data = this.studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDto(data);
    }

    public StudentDto insertNewStudent(StudentDto dto){
        Student student = this.studentMapper.toEntity(dto);
        Student newStudent = this.studentRepository.save(student);
        StudentDto response = this.studentMapper.toDto(
            this.studentRepository.findById(newStudent.getId()).
            orElseThrow(() -> new RuntimeException("Student not found")));
        return response;
    }

    public StudentDto updateStudent(Long id, StudentDto dto){
        Student student = this.studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setEnrolled(dto.isEnrolled());
        if(!dto.name().isEmpty()) student.setName(dto.name());
        if(dto.classroomId() != null) student.setClassRoom(
        this.classRoomService.findEntityById(dto.classroomId()));
        this.studentRepository.save(student);
        return this.studentMapper.toDto(student);
    }

    public void deleteStudent(Long id){
        Student student = this.studentRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Student not found"));
        this.studentRepository.delete(student);
    }

    public List<Student> findStudentsByClassRoom(Long classRoomId){
        return this.studentRepository.findByClassRoomId(classRoomId);
    }

}
