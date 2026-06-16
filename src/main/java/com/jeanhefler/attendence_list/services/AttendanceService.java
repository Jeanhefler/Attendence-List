package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceDto;
import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.mappers.AttendanceListMapper;
import com.jeanhefler.attendence_list.mappers.AttendanceMapper;
import com.jeanhefler.attendence_list.mappers.StudentMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceRepository;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private AttendanceMapper attendanceMapper;
    private StudentService studentService;
    private StudentMapper studentMapper;
    private AttendanceListService attendanceListService;
    private AttendanceListMapper attendanceListMapper;

    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper,
            StudentService studentService, StudentMapper studentMapper, AttendanceListService attendanceListService,
            AttendanceListMapper attendanceListMapper) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.attendanceListService = attendanceListService;
        this.attendanceListMapper = attendanceListMapper;
    }

    public List<AttendanceDto>findAllAttendances(){
        return this.attendanceRepository.findAll().stream().
        map(attendance -> this.attendanceMapper.toDto(attendance)).toList();
    }

    public AttendanceDto findAttendanceById(Long id){
        return this.attendanceMapper.toDto(this.attendanceRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Attendance not found")));
    }

    public AttendanceDto insertAttendance(AttendanceDto request){
        Attendance attendance = this.attendanceRepository.save(attendanceMapper.toEntity(request));
        return this.attendanceMapper.toDto(attendance);
    }
    /*is not good convert dto -> toEntity -> toDto,
    fix it using a method that return direct entity*/
    public AttendanceDto updateAttedance(Long id, AttendanceDto request){
        Attendance attendance = this.attendanceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance Not found"));
        attendance.setAttending(request.attending());
        if(request.studentId() != attendance.getId()) attendance.setStudent(
            this.studentMapper.toEntity(this.studentService.findStudentById(request.studentId())));
        if(request.attendanceListId() != null) attendance.setAttendanceList(
            this.attendanceListMapper.toEntity(
            this.attendanceListService.findAttendanceListById(request.id())));
        return this.attendanceMapper.toDto(attendance);
        
    }

    public void deleteAttendance(Long id){
        this.attendanceRepository.deleteById(id);
    }

}
