package com.vodafone.garage.dto;

import com.vodafone.garage.service.impl.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
  private String color;
  private String plate;
  private String vehicleType;
  private Integer queueNumber;

  @Override
  public String toString() {
    return "Queue Number: " + queueNumber +"  " + plate + "  " + color;
  }

  public ArrayList<Integer> allocatedSlots() {
    ArrayList<Integer> allocatedSlots = new ArrayList<>();
    for(Map.Entry<Integer, Vehicle> entry : GarageManagementServiceImpl.garage.entrySet()) {
      Integer slot = entry.getKey();
      Vehicle vehicle = entry.getValue();
      if(Objects.nonNull(vehicle) && vehicle.equals(this)) {
        allocatedSlots.add(slot);
      }
    }
    return allocatedSlots;
  }

}
