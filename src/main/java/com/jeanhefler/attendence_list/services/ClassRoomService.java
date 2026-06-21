package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.ClassRoomDto;
import com.jeanhefler.attendence_list.entities.ClassRoom;
import com.jeanhefler.attendence_list.mappers.ClassRoomMapper;
import com.jeanhefler.attendence_list.respositories.ClassRoomRepository;

@Service
public class ClassRoomService {
    private ClassRoomRepository classRoomRepository;
    private ClassRoomMapper classRoomMapper;
    private TeacherService teacherService;

    public ClassRoomService(ClassRoomRepository classRoomRepository, ClassRoomMapper classRoomMapper,
            TeacherService teacherService) {
        this.classRoomRepository = classRoomRepository;
        this.classRoomMapper = classRoomMapper;
        this.teacherService = teacherService;
    }

    public List<ClassRoomDto> findAllClassRooms(){
        return classRoomRepository.findAll().stream()
        .map(classRoomMapper::toDto).toList();
    }

    public ClassRoomDto findClassRoomById(Long id){
        ClassRoom data = this.classRoomRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Classroom Not Found"));
        ClassRoomDto response = this.classRoomMapper.toDto(data);
        return response;
    }

    public ClassRoomDto insertClassRoom(ClassRoomDto dto){
        ClassRoom newClassRoom = this.classRoomMapper.toEntity(dto);
        this.classRoomRepository.save(newClassRoom);
        ClassRoomDto response = this.classRoomMapper.toDto(newClassRoom);
        return response;
    }
   
    public ClassRoomDto updateClassRoom(Long id, ClassRoomDto dto){
        ClassRoom classRoom = this.classRoomRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Classroom not found"));
        if(!dto.name().isEmpty()) classRoom.setName(dto.name());
        if(dto.teacherId() != null) classRoom.setTeacher(
        this.teacherService.findEntityById(dto.teacherId()));
        this.classRoomRepository.save(classRoom);
        ClassRoomDto response = this.classRoomMapper.toDto(classRoom);
        return response;
    }

    public void deleteClassRoom(Long id){
        ClassRoom data = this.classRoomRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Classroom not found"));
        this.classRoomRepository.delete(data);
    }

    public ClassRoom findEntityById(Long id){
        return this.classRoomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }
}
