package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.services.AttendanceListService;

@RestController
@RequestMapping("/list")
public class AttendanceListController {
    private AttendanceListService attendanceListService;

    public AttendanceListController(AttendanceListService attendanceListService) {
        this.attendanceListService = attendanceListService;
    }
    
    @GetMapping()
    public ResponseEntity<List<AttendanceListDto>> getAll(){
        return ResponseEntity.ok().body(this.attendanceListService.getAllAttendanceLists());
    }

    @PostMapping("/{classRoomId}")
    public ResponseEntity<AttendanceListDto> insertAttendanceByClassRoom(@PathVariable Long classRoomId){
        return ResponseEntity.ok().body(this.attendanceListService
            .insertNewAttendanceListByClassRoom(classRoomId));
    }
    
}
