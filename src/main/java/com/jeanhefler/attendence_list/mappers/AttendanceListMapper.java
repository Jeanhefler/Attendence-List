package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.entities.AttendanceList;

@Mapper(componentModel = "spring", uses = {AttendanceMapper.class})
public interface AttendanceListMapper {

    @Mapping(target = "classroomId", source = "classRoom.id")
    @Mapping(target = "classroomName", source = "classRoom.name")
    @Mapping(target = "date", source = "attendanceDate")
    AttendanceListDto toDto(AttendanceList attendanceList);

    @Mapping(target = "classRoom.id", source = "classroomId")
    @Mapping(target = "classRoom.name", source = "classroomName")
    @Mapping(target = "attendanceDate", source = "date")
    AttendanceList toEntity(AttendanceListDto dto);

    @AfterMapping
    default void linkAttendances(@MappingTarget AttendanceList attendanceList) {
        if (attendanceList.getAttendances() != null) {
            attendanceList.getAttendances().forEach(attendance -> 
                attendance.setAttendanceList(attendanceList)
            );
        }
    }
}
