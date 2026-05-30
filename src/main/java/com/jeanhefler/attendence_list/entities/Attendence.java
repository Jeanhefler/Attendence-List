package com.jeanhefler.attendence_list.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendences")
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendenceId")
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private boolean isAttending;

    @ManyToOne
    @JoinColumn(name = "attendancelist_id")
    private AttendenceList attendanceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean isAttending) {
        this.isAttending = isAttending;
    }

    public AttendenceList getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendenceList attendanceList) {
        this.attendanceList = attendanceList;
    }

    
}
