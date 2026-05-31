package com.jeanhefler.attendence_list.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanhefler.attendence_list.entities.Guardian;
import com.jeanhefler.attendence_list.respositories.GuardianRepository;

@Service
public class GuardianService {
    private GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public List<Guardian> findGuardians(){
        return guardianRepository.findAll();
    }

    public Guardian findGuardianById(Long id){
        return guardianRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Guardian not Found"));
    }

    public void CreateNewGuardian(Guardian guardian){
        Guardian newGuardian = new Guardian();
        newGuardian.setName(guardian.getName());
        newGuardian.setAddress(guardian.getAddress());
        newGuardian.setPhone(guardian.getPhone());
        guardianRepository.save(newGuardian);
    }
}
