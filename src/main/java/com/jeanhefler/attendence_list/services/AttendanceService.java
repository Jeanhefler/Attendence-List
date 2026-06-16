package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceDto;
import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.mappers.AttendanceMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceRepository;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;
    private AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public List<AttendanceDto>findAllAttendances(){
        return this.attendanceRepository.findAll().stream().
        map(attendance -> this.attendanceMapper.toDto(attendance)).toList();
    }

    public AttendanceDto insertAttendance(AttendanceDto request){
        Attendance attendance = this.attendanceRepository.save(attendanceMapper.toEntity(request));
        return this.attendanceMapper.toDto(attendance);
    }

}
