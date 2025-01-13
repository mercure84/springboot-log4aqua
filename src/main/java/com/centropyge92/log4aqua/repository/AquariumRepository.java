package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AquariumRepository extends JpaRepository<Aquarium, Integer> {


    public List<Aquarium> getAllByAppUserId(int userId);

}
