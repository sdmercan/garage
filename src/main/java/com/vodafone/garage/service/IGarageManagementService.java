package com.vodafone.garage.service;

import com.vodafone.garage.dto.*;
import java.util.*;


public interface IGarageManagementService {
  /**
   *
   * @return
   */
  HashMap<String,String> findGarageStatus();

  /**
   *
   * @param vehicle
   * @return
   */
  String parkNewVehicle(Vehicle vehicle);

  /**
   *
   * @param vehicleNumber
   * @return
   */
  String leaveOneVehicle(Integer vehicleNumber);
}
