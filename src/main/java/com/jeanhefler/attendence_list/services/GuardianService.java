package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.GuardianDto;
import com.jeanhefler.attendence_list.entities.Guardian;
import com.jeanhefler.attendence_list.mappers.GuardianMapper;
import com.jeanhefler.attendence_list.respositories.GuardianRepository;

@Service
public class GuardianService {
    private GuardianRepository guardianRepository;
    private GuardianMapper guardianMapper;

    public GuardianService(GuardianRepository guardianRepository, GuardianMapper guardianMapper) {
        this.guardianRepository = guardianRepository;
        this.guardianMapper = guardianMapper;
    }

    public List<GuardianDto> findGuardians(){
        List<GuardianDto> guardians = this.guardianRepository.findAll().stream().
        map(guardian -> guardianMapper.toDto(guardian)).toList();
        return guardians;
    }

    public GuardianDto findGuardianById(Long id){
        Guardian guardian = guardianRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Guardian not Found"));
        GuardianDto response = guardianMapper.toDto(guardian);
            return response;
    }

    public GuardianDto createNewGuardian(GuardianDto guardianDto){
        Guardian data = guardianMapper.toEntity(guardianDto);
        this.guardianRepository.save(data);
        Guardian guardian = this.guardianRepository.findById(data.getId()).
        orElseThrow(() -> new RuntimeException("Guardian not found"));
        GuardianDto response = guardianMapper.toDto(guardian);
        return response;
    }

    public GuardianDto updateGuardian(GuardianDto data, Long id){
        Guardian guardian = this.guardianRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Guardian not found"));
        if(!data.name().isEmpty()) guardian.setName(data.name());
        if(!data.address().isEmpty()) guardian.setAddress(data.address());
        if(!data.phone().isEmpty()) guardian.setPhone(data.phone());
        this.guardianRepository.save(guardian);
        GuardianDto response = guardianMapper.toDto(guardian);
        return response;
    }

    public void deleteGuardianById(Long id){
        Guardian guardian = this.guardianRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Guardian not found"));
        this.guardianRepository.delete(guardian);
    }
}
