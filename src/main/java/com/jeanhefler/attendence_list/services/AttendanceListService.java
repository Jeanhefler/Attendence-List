package com.jeanhefler.attendence_list.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.entities.AttendanceList;
import com.jeanhefler.attendence_list.respositories.AttendanceListRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceListService {
    private AttendanceListRepository attendanceListRepository;
    private AttendanceService attendanceService;
    private ClassRoomService classRoomService;

    public AttendanceListService(AttendanceListRepository attendanceListRepository, AttendanceService attendanceService,
            ClassRoomService classRoomService) {
        this.attendanceListRepository = attendanceListRepository;
        this.attendanceService = attendanceService;
        this.classRoomService = classRoomService;
    }
    
    @Transactional
    public AttendanceList insertNewAttendanceListByClassRoom(Long classRoomId) {

    AttendanceList attendanceList = new AttendanceList();
    List<Attendance> attendances =
            attendanceService.getAttendancesByClassRoom(classRoomId);
    attendances.forEach(attendance ->
            attendance.setAttendanceList(attendanceList));
    attendanceList.setAttendanceDate(LocalDate.now());
    attendanceList.setClassRoom(
            classRoomService.findEntityById(classRoomId));
    attendanceList.setAttendances(attendances);
    return attendanceListRepository.save(attendanceList);
}
}
