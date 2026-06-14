package com.jeanhefler.attendence_list.dtos;

import java.time.LocalDate;
import java.util.List;

public record AttendanceListDto(
    Long id,
    LocalDate attendanceDate,
    Long classRoomId,
    List<Long> attendances
) {

}
