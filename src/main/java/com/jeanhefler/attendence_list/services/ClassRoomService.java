package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.ClassRoomDto;
import com.jeanhefler.attendence_list.entities.ClassRoom;
import com.jeanhefler.attendence_list.mappers.ClassRoomMapper;
import com.jeanhefler.attendence_list.mappers.TeacherMapper;
import com.jeanhefler.attendence_list.respositories.ClassRoomRepository;

@Service
public class ClassRoomService {
    private ClassRoomRepository classRoomRepository;
    private ClassRoomMapper classRoomMapper;
    private TeacherService teacherService;
    private TeacherMapper teacherMapper;

    

    public ClassRoomService(ClassRoomRepository classRoomRepository, ClassRoomMapper classRoomMapper,
            TeacherService teacherService, TeacherMapper teacherMapper) {
        this.classRoomRepository = classRoomRepository;
        this.classRoomMapper = classRoomMapper;
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    public List<ClassRoomDto> findAllClassRooms(){
        List<ClassRoomDto> classRooms = classRoomRepository.findAll().stream()
        .map(classRoom -> classRoomMapper.toDto(classRoom)).toList();
        return classRooms;
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
    /*is not good convert dto -> toEntity -> toDto,
    fix it using a method to return direct entity*/
    public ClassRoomDto updateClassRoom(Long id, ClassRoomDto dto){
        ClassRoom classRoom = this.classRoomRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Classroom not found"));
        if(!dto.name().isEmpty()) classRoom.setName(dto.name());
        if(dto.teacherId() != null) classRoom.setTeacher(
            teacherMapper.toEntity(this.teacherService.findTeacherById(dto.teacherId())));
        this.classRoomRepository.save(classRoom);
        ClassRoomDto response = this.classRoomMapper.toDto(classRoom);
        return response;
    }

    public void deleteClassRoom(Long id){
        ClassRoom data = this.classRoomRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Classroom not found"));
        this.classRoomRepository.delete(data);
    }
}
