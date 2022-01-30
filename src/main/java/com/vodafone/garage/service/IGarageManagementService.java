package com.vodafone.garage.service;

import org.springframework.http.*;

public interface IGarageManagementService {

  ResponseEntity<String> inquireGarageStatus(String asd);

  ResponseEntity<String> parkNewVehicle(String asd);

  ResponseEntity<String> leaveOneVehicle(String asd);
}
