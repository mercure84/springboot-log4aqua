package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.task.WaterTestReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaterTestReminderRepository extends JpaRepository<WaterTestReminder, Integer> {

    Optional<WaterTestReminder> findByAquariumId(int aquariumId);


}
