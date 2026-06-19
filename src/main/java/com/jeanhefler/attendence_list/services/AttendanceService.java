package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.respositories.AttendanceRepository;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private StudentService studentService;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentService studentService) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
    }

    List<Attendance> getAllAttendances(){
        return this.attendanceRepository.findAll();
    }

    List<Attendance> getAttendancesByClassRoom(Long classRoomId) {
        return studentService.findStudentsByClassRoom(classRoomId)
            .stream()
            .map(student -> {
                Attendance attendance = new Attendance();
                attendance.setStudent(student);
                attendance.setAttending(false);
                return attendance;
            })
            .toList();
    }   
}
