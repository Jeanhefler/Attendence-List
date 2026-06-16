package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.entities.AttendanceList;
import com.jeanhefler.attendence_list.mappers.AttendanceListMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceListRepository;

@Service
public class AttendanceListService {
    private AttendanceListRepository attendenceListRepository;
    private AttendanceListMapper attendanceListMapper;
    public AttendanceListService(AttendanceListRepository attendenceListRepository,
            AttendanceListMapper attendanceListMapper) {
        this.attendenceListRepository = attendenceListRepository;
        this.attendanceListMapper = attendanceListMapper;
    }

    public List<AttendanceListDto> findAllAttendancesList(){
        return this.attendenceListRepository.findAll().stream()
        .map(attendanceList -> this.attendanceListMapper.toDto(attendanceList)).toList();
    }

    public AttendanceListDto findAttendanceListById(Long id){
        return this.attendanceListMapper.toDto(this.attendenceListRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Attendance list not found")));
    }

    public AttendanceListDto insertNewAttendanceList(AttendanceListDto request){
        AttendanceList response = this.attendenceListRepository.save(
            this.attendanceListMapper.toEntity(request));
        return this.attendanceListMapper.toDto(response);

    }
}
