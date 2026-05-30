package com.jeanhefler.attendence_list.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanhefler.attendence_list.entities.Responsible;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long>{

}
