package com.jeanhefler.attendence_list.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendancelists")
public class AttendanceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendancelist_id")
    private Long id;
    private LocalDate Attendancedate;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "attendanceList",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Attendence> records = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ClassRoom getClassRoom() {
        return classRoom;
    }
    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
    public LocalDate getAttendanceDate() {
        return Attendancedate;
    }
    public void setAttendaceDate(LocalDate date) {
        this.Attendancedate = date;
    }
    public List<Attendence> getRecords() {
        return records;
    }
    public void setRecords(List<Attendence> records) {
        this.records = records;
    }
    
}
