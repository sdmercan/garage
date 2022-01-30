package com.vodafone.garage.common.enums;

import java.util.*;
import java.util.stream.*;
import lombok.*;

public class GeneralEnumeration {

  public enum VehicleType {
    CAR("CAR",1),
    JEEP("JEEP",2),
    TRUCK("TRUCK",4);

    @Getter
    private final String shortCode;
    @Getter
    private final int slotSize;

    VehicleType(String shortCode, int slotSize) {
      this.shortCode = shortCode;
      this.slotSize = slotSize;
    }

    public static int findVehicleSlotSize(String vehicleType) {
      return Arrays.stream(VehicleType.values())
                 .map(VehicleType::name)
                 .collect(Collectors.toList())
                 .contains(vehicleType) ? GeneralEnumeration.VehicleType.valueOf(vehicleType).getSlotSize() : 0;
    }
  }


}
