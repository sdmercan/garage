package com.vodafone.garage.service.impl;

import com.vodafone.garage.dto.*;
import com.vodafone.garage.service.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.stereotype.*;

import static com.vodafone.garage.common.constant.Constants.*;
import static com.vodafone.garage.common.enums.GeneralEnumeration.VehicleType.*;

@Service
public class GarageManagementServiceImpl implements IGarageManagementService {
  public static HashMap<Integer, Vehicle> garage;
  private static int incomingVehicle = 0;

  @Override
  public HashMap<String, String> findGarageStatus() {
    HashMap<String, String> garageStatus = new HashMap<>();
    garage.values().stream()
        .filter(Objects::nonNull)
        .filter(vehicle -> Objects.nonNull(vehicle.getPlate()))
        .forEach(vehicle -> {
          if (!garageStatus.containsValue(vehicle.toString()))
            garageStatus.put(vehicle.toString(), vehicle.allocatedSlots().toString());
        });
    return garageStatus;
  }

  @Override
  public String leaveOneVehicle(Integer vehicleNumber) {
    boolean findLeaveVehicle = Boolean.FALSE;
    for (Map.Entry<Integer, Vehicle> entry : GarageManagementServiceImpl.garage.entrySet()) {
      Integer slot = entry.getKey();
      Vehicle vehicle = entry.getValue();
      if (Objects.nonNull(vehicle) && vehicle.getQueueNumber().equals(vehicleNumber)) {
        findLeaveVehicle = Boolean.TRUE;
        garage.put(slot, null);
      }
    }
    return findLeaveVehicle ? "" : INVALID_VEHICLE_NUMBER + vehicleNumber;
  }

  @Override
  public String parkNewVehicle(Vehicle vehicle) {
    int vehicleSlotSize = findVehicleSlotSize(vehicle.getVehicleType());
    if (vehicleSlotSize == 0) {
      return INVALID_VEHICLE_TYPE;
    }
    Integer slot = isAvailableForPark(vehicleSlotSize);
    if (isAlreadyParked(vehicle))
      return ALREADY_PARKED;
    if (Objects.nonNull(slot)) {
      incomingVehicle++;
      int nextSlot = slot;
      vehicle.setQueueNumber(incomingVehicle);
      for (int i = 1; i <= vehicleSlotSize; i++) {
        garage.put(nextSlot, vehicle);
        nextSlot++;
      }
      if (nextSlot <= garage.size()) {
        Vehicle emptyVehicle = Vehicle
            .builder()
            .queueNumber(incomingVehicle)
            .build();
        garage.put(nextSlot, emptyVehicle);
      }
      return "Allocated " + vehicleSlotSize + " slots";
    }
    return GARAGE_FULL;
  }

  private boolean isAlreadyParked(Vehicle incomingVehicle) {
    return garage.values().stream()
        .filter(Objects::nonNull)
        .map(Vehicle::getPlate)
        .filter(Objects::nonNull)
        .collect(Collectors.toList())
        .contains(incomingVehicle.getPlate());
  }

  private Integer isAvailableForPark(int vehicleSlotSize) {
    int slotNull = 0;
    for (Integer slot : garage.keySet()) {
      if (Objects.isNull(garage.get(slot))) {
        slotNull++;
        if (slot.equals(GARAGE_SIZE) && (Objects.equals(slotNull, vehicleSlotSize))) {
          return slot - (vehicleSlotSize - 1);
        } else if (slotNull == vehicleSlotSize + 1)
          return slot - vehicleSlotSize;
      } else
        slotNull = 0;
    }
    return null;
  }


  public static void setUpGarage() {
    garage = new HashMap<>();
    for (int i = 1; i <= GARAGE_SIZE; i++) {
      garage.put(i, null);
    }
  }
}
