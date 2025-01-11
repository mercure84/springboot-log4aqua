package com.centropyge92.log4aqua.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String password;

    private Date signupDate = new Date();
    private String role = "USER";
    private MemberStatus memberStatus;

    public enum MemberStatus {VALIDATION_EMAIL, BANNED, EMAIL_CONFIRMED, BLOCKED }

}
