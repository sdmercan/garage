package com.vodafone.garage.dto;

import com.vodafone.garage.enums.GeneralEnumeration.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
  private String color;
  private String plate;
  private VehicleType vehicleType;

}
