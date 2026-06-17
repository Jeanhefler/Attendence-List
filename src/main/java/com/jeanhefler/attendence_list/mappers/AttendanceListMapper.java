package com.jeanhefler.attendence_list.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jeanhefler.attendence_list.dtos.AttendanceListDto;
import com.jeanhefler.attendence_list.entities.Attendance;
import com.jeanhefler.attendence_list.entities.AttendanceList;
import com.jeanhefler.attendence_list.entities.ClassRoom;

@Mapper(componentModel = "spring")
public interface AttendanceListMapper {

    @Mapping(source = "classRoom.id", target = "classRoomId")
    AttendanceListDto toDto(AttendanceList entity);

    @Mapping(source = "classRoomId", target = "classRoom.")
    @Mapping(source = "attendanceDate", target = "attendanceDate")

    AttendanceList toEntity(AttendanceListDto attendanceListDto);

    default List<Long> mapAttedance(List<Attendance> attendences) {
        if (attendences == null) {
            return null;
        }
        return attendences.stream()
                .map(Attendance::getId)
                .toList();
    }

    default Long mapClassRoom(ClassRoom classRoom) {
        return classRoom != null ? classRoom.getId() : null;
    }

    default ClassRoom mapClassRoom(Long id) {
        if (id == null) {
            return null;
        }

        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(id);
        return classRoom;
    }

    default List<Attendance> mapAttendance(List<Long> ids) {
    if (ids == null) {
        return null;
    }

    return ids.stream()
            .map(this::mapAttendance)
            .toList();
}

    default Attendance mapAttendance(Long id) {
    if (id == null) {
        return null;
    }

    Attendance attendance = new Attendance();
    attendance.setId(id);
    return attendance;
}
}
