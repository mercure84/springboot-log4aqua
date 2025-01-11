package com.centropyge92.log4aqua.model.living;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Plant extends Living {

    private String origin;



}
