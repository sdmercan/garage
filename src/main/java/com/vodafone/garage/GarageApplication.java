package com.vodafone.garage;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

import static com.vodafone.garage.service.impl.GarageManagementServiceImpl.*;

@SpringBootApplication
public class GarageApplication {

  public static void main(String[] args) {
    SpringApplication.run(GarageApplication.class, args);
    setUpGarage();
  }

}
