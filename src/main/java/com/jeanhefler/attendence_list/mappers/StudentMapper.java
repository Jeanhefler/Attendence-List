package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jeanhefler.attendence_list.dtos.StudentDto;
import com.jeanhefler.attendence_list.entities.ClassRoom;
import com.jeanhefler.attendence_list.entities.Guardian;
import com.jeanhefler.attendence_list.entities.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "guardian.id", target = "guardianId")
    @Mapping(source = "classRoom.id", target = "classroomId")
    StudentDto toDto(Student student);

    @Mapping(source = "guardianId", target = "guardian")
    @Mapping(source = "classroomId", target = "classRoom")
    Student toEntity(StudentDto dto);

    default Guardian mapGuardian(Long id) {
        if (id == null) {
            return null;
        }

        Guardian guardian = new Guardian();
        guardian.setId(id);
        return guardian;
    }

    default Long mapGuardian(Guardian guardian) {
        return guardian != null ? guardian.getId() : null;
    }

    default ClassRoom mapClassRoom(Long id) {
        if (id == null) {
            return null;
        }

        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(id);
        return classRoom;
    }

    default Long mapClassRoom(ClassRoom classRoom) {
        return classRoom != null ? classRoom.getId() : null;
    }
}
