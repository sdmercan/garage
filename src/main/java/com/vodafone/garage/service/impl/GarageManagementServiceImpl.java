package com.vodafone.garage.service.impl;

import com.vodafone.garage.enums.*;
import com.vodafone.garage.service.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Service
public class GarageManagementServiceImpl implements IGarageManagementService {

  @Override
  public ResponseEntity<String> inquireGarageStatus(String asd) {
    return null;
  }

  @Override
  public ResponseEntity<String> parkNewVehicle(String asd) {
    return null;
  }

  @Override
  public ResponseEntity<String> leaveOneVehicle(String asd) {
    return null;
  }
}
