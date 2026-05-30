package com.jeanhefler.attendence_list.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanhefler.attendence_list.entities.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long>{

}
