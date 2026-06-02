package com.jeanhefler.attendence_list.entities;

import java.util.ArrayList;
import java.util.List;

import com.jeanhefler.attendence_list.dtos.TeacherDto;

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

    public Teacher(TeacherDto data){
        this.setId(data.id());
        this.setName(data.name());
        this.setAddress(data.address());
        this.setPhone(data.phone());
    }

    @OneToMany(mappedBy = "teacher")
    private List<ClassRoom> classRooms = new ArrayList<>();
}
