package com.jeanhefler.attendence_list.entities;

import java.time.LocalDate;
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
    @Column(name = "attendancedate")
    private LocalDate AttendanceDate;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @OneToMany(
    mappedBy = "attendanceList",
    cascade = CascadeType.ALL)
    private List<Attendance> attendances;
    
    public AttendanceList() {}
    
    public AttendanceList(Long id, LocalDate attendanceDate, ClassRoom classRoom) {
        this.id = id;
        AttendanceDate = attendanceDate;
        this.classRoom = classRoom;
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return AttendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        AttendanceDate = attendanceDate;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

}
