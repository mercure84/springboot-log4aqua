package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingsRepository extends JpaRepository<AppSettings, Integer> {

    AppSettings findById(int id);


}
