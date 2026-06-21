package com.jeanhefler.attendence_list.dtos;

public record AttendanceDto(
    Long id,
    Long studentId,
    String studentName,
    boolean attending
) {

}
