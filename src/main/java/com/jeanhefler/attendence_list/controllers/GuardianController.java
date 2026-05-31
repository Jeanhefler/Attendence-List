package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.attendence_list.dtos.GuardianDto;
import com.jeanhefler.attendence_list.entities.Guardian;
import com.jeanhefler.attendence_list.services.GuardianService;

@RestController
@RequestMapping("guardian")
public class GuardianController {
    private GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }
    
    @GetMapping("/guardians")
    public ResponseEntity<List<GuardianDto>> getGuardians(){
        List<GuardianDto> guardianDtos = guardianService.findGuardians().stream()
        .map(guardian -> new GuardianDto(
            guardian.getId(),
            guardian.getName(),
            guardian.getPhone(),
            guardian.getAddress())).toList();
        return ResponseEntity.ok(guardianDtos);
    }

    @GetMapping("/{id}") 
    ResponseEntity<GuardianDto> getGuardianById (@PathVariable Long id){
        Guardian guardian = guardianService.findGuardianById(id);
        GuardianDto dto = new GuardianDto(
            guardian.getId(),
            guardian.getName(),
            guardian.getPhone(),
            guardian.getAddress());
        return ResponseEntity.ok(dto);
    }
}
