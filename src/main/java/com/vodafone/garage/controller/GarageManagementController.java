package com.vodafone.garage.controller;

import com.vodafone.garage.service.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/garage")
public class GarageManagementController {

  private final IGarageManagementService garageManagementService;

  public GarageManagementController(IGarageManagementService garageManagementService) {
    this.garageManagementService = garageManagementService;
  }

  @PostMapping(value = "/inquireGarageStatus")
  public ResponseEntity<String> inquireGarageStatus(String asd) {
    return garageManagementService.inquireGarageStatus(asd);
  }

  @PostMapping(value = "/parkNewVehicle")
  public ResponseEntity<String> parkNewVehicle(String asd) {
    return garageManagementService.parkNewVehicle(asd);
  }

  @GetMapping(value = "/leaveVehicle")
  public ResponseEntity<String> leaveVehicle(String asd) {
    return garageManagementService.leaveOneVehicle(asd);
  }
}
