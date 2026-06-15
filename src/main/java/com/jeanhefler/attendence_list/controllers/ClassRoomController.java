package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.attendence_list.dtos.ClassRoomDto;
import com.jeanhefler.attendence_list.services.ClassRoomService;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {
    private ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }
    
    @GetMapping()
    public ResponseEntity<List<ClassRoomDto>> getAll(){
        List<ClassRoomDto> response = this.classRoomService.findAllClassRooms();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoomDto> getById(@PathVariable Long id){
        ClassRoomDto response = this.classRoomService.findClassRoomById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping()
    public ResponseEntity<ClassRoomDto> insert(@RequestBody ClassRoomDto dto){
        ClassRoomDto response = this.classRoomService.insertClassRoom(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomDto> update(@PathVariable Long id, @RequestBody ClassRoomDto dto){
        ClassRoomDto response = this.classRoomService.updateClassRoom(id, dto);
        return ResponseEntity.ok().body(response);
    }
}
