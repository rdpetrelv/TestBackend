package com.laundry.laundryMgmt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaundryMgmtApplication {

    private static final Logger logger = LogManager.getLogger(LaundryMgmtApplication.class);
    public static Marker LogToMachinesLogFile = MarkerManager.getMarker("LogToMachinesLogFile");

    public static void main(String[] args) {
        SpringApplication.run(LaundryMgmtApplication.class, args);
        logger.info(LogToMachinesLogFile, "This log contains the basic log information of requests made to server on the machines API");
    }

}
