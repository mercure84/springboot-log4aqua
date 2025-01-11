package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {



}
