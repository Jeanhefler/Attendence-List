package com.jeanhefler.attendence_list.entities;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "teacher")
    private List<ClassRoom> classRooms = new ArrayList<>();
}
