package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jeanhefler.attendence_list.dtos.ClassRoomDto;
import com.jeanhefler.attendence_list.entities.ClassRoom;

@Mapper(componentModel = "spring")
public interface ClassRoomMapper {
    
    @Mapping(source = "teacher.id", target = "teacherId")
    ClassRoomDto toDto(ClassRoom entity);

    @Mapping(source = "teacherId", target = "teacher.id")
    ClassRoom toEntity(ClassRoomDto dto);
}
