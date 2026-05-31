package com.jeanhefler.attendence_list.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "guardians")
@AttributeOverride(name = "id", column = @Column(name = "guardian_id"))
public class Guardian extends StakeHolder{

    public Guardian(){

    }
    
    public Guardian(Long id, String name, String phone, String address) {
        super(id, name, phone, address);
    }

    @OneToMany(mappedBy = "guardian")
    private List<Student> students = new ArrayList<>();
}
