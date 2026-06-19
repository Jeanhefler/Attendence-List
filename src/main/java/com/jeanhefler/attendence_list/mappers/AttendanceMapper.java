package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jeanhefler.attendence_list.dtos.AttendanceDto;
import com.jeanhefler.attendence_list.entities.Attendance;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "attending", target = "attending")
    AttendanceDto toDto(Attendance entity);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "attending", target = "attending")
    Attendance toEntity(AttendanceDto dto);
}
