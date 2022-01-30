package com.vodafone.garage.enums;

import lombok.*;

public class GeneralEnumeration {

  public enum VehicleType {
    CAR("CAR",1),
    JEEP("JEEP",2),
    TRUCK("TRUCK",4);

    @Getter
    private final String shortCode;
    @Getter
    private final long slotSize;

    VehicleType(String shortCode, long slotSize) {
      this.shortCode = shortCode;
      this.slotSize = slotSize;
    }
  }


}
