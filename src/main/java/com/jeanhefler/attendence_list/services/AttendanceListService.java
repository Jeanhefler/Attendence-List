package com.jeanhefler.attendence_list.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.AttendanceDto;
import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.entities.AttendanceList;
import com.jeanhefler.attendence_list.entities.ClassRoom;
import com.jeanhefler.attendence_list.mappers.AttendanceListMapper;
import com.jeanhefler.attendence_list.mappers.AttendanceMapper;
import com.jeanhefler.attendence_list.respositories.AttendanceListRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceListService {
    private AttendanceListRepository attendanceListRepository;
    private AttendanceService attendanceService;
    private AttendanceMapper attendanceMapper;
    private AttendanceListMapper attendanceListMapper;
    private ClassRoomService classRoomService;

    public AttendanceListService(AttendanceListRepository attendanceListRepository, AttendanceService attendanceService,
            AttendanceMapper attendanceMapper, AttendanceListMapper attendanceListMapper,
            ClassRoomService classRoomService) {
        this.attendanceListRepository = attendanceListRepository;
        this.attendanceService = attendanceService;
        this.attendanceMapper = attendanceMapper;
        this.attendanceListMapper = attendanceListMapper;
        this.classRoomService = classRoomService;
    }

    public List<AttendanceListDto> getAllAttendanceLists(){
        return this.attendanceListRepository.findAllWithDetails().stream()
        .map(this.attendanceListMapper::toDto).toList();
    }

    public AttendanceListDto getAttendanceListById(Long id){
        return this.attendanceListMapper.toDto(this.attendanceListRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance list not found")));
    }

    public List<AttendanceListDto> getAttendanceListsByClassRoomId(Long classroomId){
        return this.attendanceListRepository.findByClassRoomId(classroomId)
            .stream().map(this.attendanceListMapper::toDto).toList();
    }
    
    @Transactional
    public AttendanceListDto insertNewAttendanceListByClassRoom(Long classRoomId) {
        ClassRoom classRoom = this.classRoomService.findEntityById(classRoomId);
        List<AttendanceDto> attendances = attendanceService.getAttendancesByClassRoom(classRoom.getId());
        AttendanceListDto attendanceList = new AttendanceListDto(
            classRoom.getId(),
            classRoom.getName(),
            LocalDate.now(),
            attendances
        );
        return this.attendanceListMapper.toDto(
            this.attendanceListRepository.save(this.attendanceListMapper.toEntity(attendanceList)));
    }

    @Transactional
    public AttendanceListDto updateAttendanceList(Long id, AttendanceListDto attendanceListDto){
        AttendanceList attendanceList = this.attendanceListRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance list not found"));
        if(attendanceListDto.date() != attendanceList.getAttendanceDate()){ 
            attendanceList.setAttendanceDate(attendanceListDto.date());
        }
        if(attendanceListDto.classroomId() != attendanceList.getClassRoom().getId()){
            attendanceList.setClassRoom(this.classRoomService.findEntityById(attendanceListDto.classroomId()));
            attendanceList.setAttendances(this.attendanceService.getAttendancesByClassRoom(id).stream()
            .map(this.attendanceMapper::toEntity).toList());
        }
        
        return this.attendanceListMapper.toDto(this.attendanceListRepository.save(attendanceList));  
        
    }

    @Transactional
    public void deleteAttendanceList(Long id){
        AttendanceList attendanceList = this.attendanceListRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance list not found"));
        this.attendanceListRepository.delete(attendanceList);
    }
        
}
