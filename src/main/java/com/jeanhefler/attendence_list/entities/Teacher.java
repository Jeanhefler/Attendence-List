package com.jeanhefler.attendence_list.entities;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
@AttributeOverride(name = "id", column = @Column(name = "teacher_id"))
public class Teacher extends StakeHolder {
    public Teacher(){}
    public Teacher(Long id, String name, String phone, String address) {
        super(id, name, phone, address);
    }


    @OneToMany(mappedBy = "teacher")
    private List<ClassRoom> classRooms;
}
