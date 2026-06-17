package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.entities.AttendanceList;
import com.jeanhefler.attendence_list.mappers.AttendanceListMapper;
import com.jeanhefler.attendence_list.mappers.ClassRoomMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceListRepository;

@Service
public class AttendanceListService {
    private AttendanceListRepository attendanceListRepository;
    private AttendanceListMapper attendanceListMapper;
    private ClassRoomService classRoomService;
    private ClassRoomMapper classRoomMapper;

    public AttendanceListService(AttendanceListRepository attendanceListRepository,
            AttendanceListMapper attendanceListMapper, ClassRoomService classRoomService,
            ClassRoomMapper classRoomMapper) {
        this.attendanceListRepository = attendanceListRepository;
        this.attendanceListMapper = attendanceListMapper;
        this.classRoomService = classRoomService;
        this.classRoomMapper = classRoomMapper;
    }

    public List<AttendanceListDto> findAllAttendancesList(){
        return this.attendanceListRepository.findAll().stream()
        .map(attendanceList -> this.attendanceListMapper.toDto(attendanceList)).toList();
    }

    public AttendanceListDto findAttendanceListById(Long id){
        return this.attendanceListMapper.toDto(this.attendanceListRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Attendance list not found")));
    }

    public AttendanceListDto insertNewAttendanceList(AttendanceListDto request){
        AttendanceList response = this.attendanceListRepository.save(
            this.attendanceListMapper.toEntity(request));
        return this.attendanceListMapper.toDto(response);
    }

    /*is not good convert dto -> toEntity -> toDto,
    fix it using a method that return direct entity*/
    public AttendanceListDto updateAttendanceList(Long id, AttendanceListDto dto){
        AttendanceList attendanceList = this.attendanceListRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Attendance list not found"));
        if(dto.attendanceDate() != attendanceList.getAttendanceDate()) 
            attendanceList.setAttendanceDate(attendanceList.getAttendanceDate());
        if(dto.classRoomId() != attendanceList.getClassRoom().getId())
            attendanceList.setClassRoom(this.classRoomMapper.
        toEntity(this.classRoomService.findClassRoomById(id)));
        this.attendanceListRepository.save(attendanceList);
        return this.attendanceListMapper.toDto(attendanceList);
    }

    public void deleteAttendanceList(Long id){
        AttendanceList attendanceList = this.attendanceListRepository.findById(id).
        orElseThrow(() -> new RuntimeException("List not found"));
        this.attendanceListRepository.deleteById(attendanceList.getId());
    }

    public AttendanceList findEntityById(Long id) {
    return attendanceListRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance List not found"));
    }

}
