package com.jeanhefler.attendence_list.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanhefler.attendence_list.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    List<Student> findByClassRoomId(Long classroomId);

}
