package com.jeanhefler.attendence_list.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jeanhefler.attendence_list.entities.AttendanceList;

@Repository
public interface AttendanceListRepository extends JpaRepository<AttendanceList, Long>{
    @Query("SELECT al FROM AttendanceList al " +
           "JOIN FETCH al.classRoom " +
           "LEFT JOIN FETCH al.attendances a " +
           "LEFT JOIN FETCH a.student")
    List<AttendanceList> findAllWithDetails();

    List<AttendanceList> findByClassRoomId(Long classroomId);
}
