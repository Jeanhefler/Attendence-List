package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceDto;
import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.mappers.AttendanceMapper;
import com.jeanhefler.attendence_list.mappers.StudentMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceRepository;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private StudentService studentService;
    private AttendanceMapper attendanceMapper;
    private StudentMapper studentMapper; 

    public AttendanceService(AttendanceRepository attendanceRepository, StudentService studentService,
            AttendanceMapper attendanceMapper, StudentMapper studentMapper) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
        this.attendanceMapper = attendanceMapper;
        this.studentMapper = studentMapper;
    }

    List<AttendanceDto> getAllAttendances(){
        return this.attendanceRepository.findAll().stream()
            .map(this.attendanceMapper::toDto).toList();
    }

    public AttendanceDto getAttendanceById(Long id){
        return this.attendanceMapper.toDto(this.attendanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found")));
    }

    AttendanceDto updateAttendance(Long id, AttendanceDto dto){
        Attendance attendance = this.attendanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendance.setAttending(dto.attending());
        if (dto.studentId() != attendance.getStudent().getId()) {
            attendance.setStudent(this.studentMapper.toEntity(
                this.studentService.findStudentById(dto.studentId())));
        }
        return this.attendanceMapper.toDto(this.attendanceRepository.save(attendance));
    }

    public void deleteAttendance(Long id){
        Attendance attendance = this.attendanceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found"));
        this.attendanceRepository.delete(attendance);
    }

        List<AttendanceDto> getAttendancesByClassRoom(Long classRoomId) {
        return studentService.findStudentsByClassRoom(classRoomId)
            .stream()
            .map(student -> {
                AttendanceDto attendance = new AttendanceDto(
                    null,
                    student.id(),
                    student.name(),
                    true
                );
                return attendance;
            })
            .toList();
    }
    
}
