package com.vodafone.garage.controller;

import com.vodafone.garage.common.enums.*;
import com.vodafone.garage.dto.*;
import com.vodafone.garage.service.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GarageManagementControllerTest {

  @Mock
  IGarageManagementService garageManagementService;

  @InjectMocks
  GarageManagementController garageManagementController;

  @Test
  public void findGarageStatus() {
    HashMap<String, String> returnValue = new HashMap<String, String>(){{
      put("key1", "value1");
    }};

    when(garageManagementService.findGarageStatus()).thenReturn(returnValue);

    ResponseEntity<HashMap<String, String>> result = garageManagementController.findGarageStatus();
    Assertions.assertEquals(1L, Objects.requireNonNull(result.getBody()).size());
  }

  @Test
  public void parkNewVehicle() {
    Vehicle vehicle = Vehicle.builder().vehicleType(GeneralEnumeration.VehicleType.CAR.getShortCode()).build();
    String returnValue = "Success";

    when(garageManagementService.parkNewVehicle(vehicle)).thenReturn(returnValue);

    ResponseEntity<String> result = garageManagementController.parkNewVehicle(vehicle);
    Assertions.assertEquals(returnValue, Objects.requireNonNull(result.getBody()));
  }

  @Test
  public void leaveVehicle() {
    int vehicleNumber = 1;
    String returnValue = "Success";

    when(garageManagementService.leaveOneVehicle(vehicleNumber)).thenReturn(returnValue);

    ResponseEntity<String> result = garageManagementController.leaveVehicle(vehicleNumber);
    Assertions.assertEquals(returnValue, result.getBody());
  }
}