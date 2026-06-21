package com.jeanhefler.attendence_list.dtos;

import java.time.LocalDate;
import java.util.List;

public record AttendanceListDto(
    Long classroomId,
    String classroomName,
    LocalDate date,
    List<AttendanceDto> attendances
) {

}
