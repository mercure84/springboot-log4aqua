package com.centropyge92.log4aqua.model.aquarium;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FreshWaterAquarium extends Aquarium {


}
