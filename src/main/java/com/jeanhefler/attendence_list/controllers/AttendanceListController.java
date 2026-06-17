package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.services.AttendanceListService;

@RestController
@RequestMapping("/attendancelist")
public class AttendanceListController {
    private AttendanceListService attendanceListService;

    public AttendanceListController(AttendanceListService attendanceListService) {
        this.attendanceListService = attendanceListService;
    }

    @GetMapping()
    public ResponseEntity<List<AttendanceListDto>> getAll(){
        return ResponseEntity.ok().body(this.attendanceListService.findAllAttendancesList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceListDto> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.attendanceListService.findAttendanceListById(id));
    }

    @PostMapping()
    public ResponseEntity<AttendanceListDto> insert(@RequestBody AttendanceListDto dto){
        return ResponseEntity.ok().body(this.attendanceListService.insertNewAttendanceList(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceListDto> update(@PathVariable Long id, 
        @RequestBody AttendanceListDto dto){
        return ResponseEntity.ok().body(this.attendanceListService.updateAttendanceList(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AttendanceListDto> delete(@PathVariable Long id){
        this.attendanceListService.deleteAttendanceList(id);
        return ResponseEntity.noContent().build();
    }
}
