package com.vodafone.garage.service.impl;

import com.vodafone.garage.common.enums.*;
import com.vodafone.garage.dto.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import static com.vodafone.garage.common.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
public class GarageManagementServiceImplTest {

  @InjectMocks
  GarageManagementServiceImpl garageManagementService;

  Vehicle vehicle;

  @BeforeEach
  public void prepareTestData() {
    GarageManagementServiceImpl.setUpGarage();
    vehicle = Vehicle.builder()
        .vehicleType(GeneralEnumeration.VehicleType.CAR.getShortCode())
        .plate("06VDN60")
        .queueNumber(1)
        .build();
    GarageManagementServiceImpl.garage.put(1, vehicle);
  }

  @Test
  public void findGarageStatus() {
    HashMap<String, String> result = garageManagementService.findGarageStatus();
    Assertions.assertEquals(1, result.size());
  }

  @Test
  public void leaveOneVehicle() {
    String result = garageManagementService.leaveOneVehicle(1);
    Assertions.assertTrue(result.isEmpty(), result);
  }

  @Test
  public void parkNewVehicleForAlreadyParkedCondition() {
    String result = garageManagementService.parkNewVehicle(vehicle);
    Assertions.assertEquals(ALREADY_PARKED, result);
  }

  @Test
  public void parkNewVehicleForInvalidVehicleCondition() {
    vehicle.setVehicleType("BUS");

    String result = garageManagementService.parkNewVehicle(vehicle);
    Assertions.assertEquals(INVALID_VEHICLE_TYPE, result);
  }

  @Test
  public void parkNewVehicleForAvailableCondition() {
    Vehicle newVehicle = Vehicle.builder()
        .vehicleType(GeneralEnumeration.VehicleType.JEEP.getShortCode())
        .plate("06VDN34").build();

    String result = garageManagementService.parkNewVehicle(newVehicle);
    Assertions.assertEquals("Allocated 2 slots", result);
  }

  @Test
  public void parkNewVehicleForFullCondition() {
    Vehicle newVehicle = Vehicle.builder()
        .vehicleType(GeneralEnumeration.VehicleType.JEEP.getShortCode())
        .plate("06VDN40").build();
    for (int i = 1; i < 10; i++) {
      newVehicle.setPlate("06VDN3" + i);
      GarageManagementServiceImpl.garage.put(i, newVehicle);
    }
    Vehicle vehicleParam = Vehicle.builder()
        .vehicleType(GeneralEnumeration.VehicleType.TRUCK.getShortCode())
        .plate("06VDN60").build();
    String result = garageManagementService.parkNewVehicle(vehicleParam);
    Assertions.assertEquals(GARAGE_FULL, result);
  }
}