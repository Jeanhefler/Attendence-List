package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.StudentDto;
import com.jeanhefler.attendence_list.entities.Student;
import com.jeanhefler.attendence_list.mappers.ClassRoomMapper;
import com.jeanhefler.attendence_list.mappers.GuardianMapper;
import com.jeanhefler.attendence_list.mappers.StudentMapper;
import com.jeanhefler.attendence_list.respositories.StudentRepository;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private GuardianService guardianService;
    private GuardianMapper guardianMapper;
    private ClassRoomService classRoomService;
    private ClassRoomMapper classRoomMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
            GuardianService guardianService, GuardianMapper guardianMapper, ClassRoomService classRoomService,
            ClassRoomMapper classRoomMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.guardianService = guardianService;
        this.guardianMapper = guardianMapper;
        this.classRoomService = classRoomService;
        this.classRoomMapper = classRoomMapper;
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

    /*is not good convert dto -> toEntity -> toDto,
    fix it using a method that return direct entity*/
    public StudentDto updateStudent(Long id, StudentDto dto){
        Student student = this.studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setEnrolled(dto.isEnrolled());
        if(!dto.name().isEmpty()) student.setName(dto.name());
        if(dto.guardianId() != null) student.setGuardian(this.guardianMapper.
            toEntity(this.guardianService.findGuardianById(dto.guardianId())));
        if(dto.classroomId() != null) student.setClassRoom(this.classRoomMapper.
            toEntity(this.classRoomService.findClassRoomById(dto.classroomId())));
        this.studentRepository.save(student);
        return this.studentMapper.toDto(student);
    }

    public void deleteStudent(Long id){
        Student student = this.studentRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Student not found"));
        this.studentRepository.delete(student);
    }

}
