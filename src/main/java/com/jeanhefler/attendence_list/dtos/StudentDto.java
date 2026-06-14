package com.jeanhefler.attendence_list.dtos;

public record StudentDto(
    Long id,
    String name,
    boolean isEnrolled,
    Long guardianId,
    Long classroomId
) {

}
