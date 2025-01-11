package com.centropyge92.log4aqua.model.living;



import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public abstract class Animal extends Living {

    private Date birthDate;
    private Sex sex = Sex.UNDEFINED;
    private enum Sex {MALE, FEMALE, UNDEFINED}



}