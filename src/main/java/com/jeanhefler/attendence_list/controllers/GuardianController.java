package com.jeanhefler.attendence_list.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping()
    public ResponseEntity<List<GuardianDto>> getGuardians(){
        List<GuardianDto> guardianDtos = this.guardianService.findGuardians().stream()
        .map(guardian -> new GuardianDto(
            guardian.getId(),
            guardian.getName(),
            guardian.getPhone(),
            guardian.getAddress())).toList();
        return ResponseEntity.ok(guardianDtos);
    }

    @GetMapping("/{id}") 
    ResponseEntity<GuardianDto> getGuardianById(@PathVariable Long id){
        Guardian guardian = this.guardianService.findGuardianById(id);
        GuardianDto dto = new GuardianDto(
            guardian.getId(),
            guardian.getName(),
            guardian.getPhone(),
            guardian.getAddress());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    ResponseEntity<Guardian> insert(@RequestBody GuardianDto guardianDto){
        Guardian guardian = this.guardianService.createNewGuardian(guardianDto);
        return ResponseEntity.ok().body(guardian);
    }

    @PutMapping("/{id}")
    ResponseEntity<Guardian> updateGuardian(@RequestBody GuardianDto data, @PathVariable Long id){
        Guardian guardian = this.guardianService.updateGuardian(data, id);
        return ResponseEntity.ok().body(guardian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Guardian> deleteGuardian(@PathVariable Long id){
        this.guardianService.deleteGuardianById(id);
        return ResponseEntity.noContent().build();
    }
}
