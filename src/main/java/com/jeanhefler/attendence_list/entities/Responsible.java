package com.jeanhefler.attendence_list.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsibles")
@AttributeOverride(name = "id", column = @Column(name = "responsible_id"))
public class Responsible extends StakeHolder{
    @OneToMany(mappedBy = "responsible")
    private List<Student> students = new ArrayList<>();
}
