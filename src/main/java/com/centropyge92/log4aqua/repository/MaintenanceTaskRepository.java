package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.task.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Integer> {



}
