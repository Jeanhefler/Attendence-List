package com.jeanhefler.attendence_list.mappers;

import org.mapstruct.Mapper;

import com.jeanhefler.attendence_list.dtos.GuardianDto;
import com.jeanhefler.attendence_list.entities.Guardian;

@Mapper(componentModel = "spring")
public interface GuardianMapper {

    GuardianDto toDto(Guardian entity);

    Guardian toEntity(GuardianDto dto);
}
