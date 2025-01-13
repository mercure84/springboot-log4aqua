package com.centropyge92.log4aqua.model;

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
    private String lastName;
    private String firstName;
    private String userName;
    private String email;
    private int themeColor;

    private String provider;
    private String providerId;

    private Date signupDate = new Date();
    private String role = "USER";


}
