package com.jeanhefler.attendence_list.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    private boolean isAttending;

    @ManyToOne
    @JoinColumn(name = "attendancelist_id")
    private AttendanceList attendanceList;

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

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceList attendanceList) {
        this.attendanceList = attendanceList;
    }

    
}
