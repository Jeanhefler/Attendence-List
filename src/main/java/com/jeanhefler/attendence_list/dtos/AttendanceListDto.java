package com.jeanhefler.attendence_list.dtos;

import java.time.LocalDate;

public record AttendanceListDto(
    Long id,
    LocalDate attendanceDate,
    Long classRoomId
) {

}
