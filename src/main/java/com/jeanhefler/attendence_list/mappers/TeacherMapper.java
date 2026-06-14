package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.Mapper;

import com.jeanhefler.attendence_list.dtos.TeacherDto;
import com.jeanhefler.attendence_list.entities.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto toDto(Teacher entity);

    Teacher toEntity(TeacherDto dto);
}
