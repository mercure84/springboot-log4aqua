package com.centropyge92.log4aqua.model;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Date signupDate = new Date();
    private String role = "MEMBER";

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Aquarium> aquariums = new ArrayList<Aquarium>();

    @OneToOne(cascade = CascadeType.ALL)
    private AppSettings appSettings;

}
