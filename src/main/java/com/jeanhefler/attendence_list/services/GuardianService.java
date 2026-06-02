package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.dtos.GuardianDto;
import com.jeanhefler.attendence_list.entities.Guardian;
import com.jeanhefler.attendence_list.respositories.GuardianRepository;

@Service
public class GuardianService {
    private GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public GuardianDto toDto(Guardian guardian){
        GuardianDto response = new GuardianDto(
            guardian.getId(),
            guardian.getName(),
            guardian.getPhone(),
            guardian.getAddress()
        );
        return response;
    }

    public List<Guardian> findGuardians(){
        return this.guardianRepository.findAll();
    }

    public Guardian findGuardianById(Long id){
        return this.guardianRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Guardian not Found"));
    }

    public Guardian createNewGuardian(GuardianDto guardianDto){
        Guardian newGuardian = new Guardian(guardianDto);
        this.guardianRepository.save(newGuardian);
        return newGuardian;
    }

    public Guardian updateGuardian(GuardianDto data, Long id){
        Guardian guardian = this.findGuardianById(id);
        if(!data.name().isEmpty()) guardian.setName(data.name());
        if(!data.address().isEmpty()) guardian.setAddress(data.address());
        if(!data.phone().isEmpty()) guardian.setPhone(data.phone());
        this.guardianRepository.save(guardian);
        return guardian;
    }

    public void deleteGuardianById(Long id){
        Guardian guardian = this.findGuardianById(id);
        this.guardianRepository.delete(guardian);
    }
}
