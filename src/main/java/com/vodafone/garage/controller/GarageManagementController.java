package com.vodafone.garage.controller;

import com.vodafone.garage.dto.*;
import com.vodafone.garage.service.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/garage")
public class GarageManagementController {

  private final IGarageManagementService garageManagementService;

  public GarageManagementController(IGarageManagementService garageManagementService) {
    this.garageManagementService = garageManagementService;
  }

  @GetMapping(value = "/findGarageStatus")
  public ResponseEntity<HashMap<String,String>> findGarageStatus() {
    return new ResponseEntity<>(garageManagementService.findGarageStatus(),HttpStatus.OK);
  }

  @PostMapping(value = "/parkNewVehicle")
  public ResponseEntity<String> parkNewVehicle(@RequestBody Vehicle vehicle) {
    return new ResponseEntity<>(garageManagementService.parkNewVehicle(vehicle),HttpStatus.OK);
  }

  @PostMapping(value = "/leaveVehicle")
  public ResponseEntity<String> leaveVehicle(@RequestParam("vehicleNumber") Integer vehicleNumber) {
    return new ResponseEntity<>(garageManagementService.leaveOneVehicle(vehicleNumber), HttpStatus.OK);
  }
}
