package com.vodafone.garage.service;

import com.vodafone.garage.dto.*;
import java.util.*;


public interface IGarageManagementService {

  /**
   * @return hashmap include garage status
   */
  HashMap<String,String> findGarageStatus();

  /**
   * @param vehicle for parking process
   * @return allocated slot
   */
  String parkNewVehicle(Vehicle vehicle);

  /**
   * @param vehicleNumber for leave process
   * @return string if doesnt find vehicle
   */
  String leaveOneVehicle(Integer vehicleNumber);
}
