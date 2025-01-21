package com.centropyge92.log4aqua.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue
    private int id;
    private String userName;

    @Column(unique = true)
    private String email;
    private int themeColor = 0;

    private String provider;
    private String providerId;

    private Date signupDate = new Date();
    private String role = "MEMBER";


}
