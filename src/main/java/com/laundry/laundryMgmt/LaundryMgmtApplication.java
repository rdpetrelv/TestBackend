/**
 © 2024 Laundary Management
 This file is under the MIT License.
 See the LICENSE file at the root of the project for more details.
  */

package com.laundry.laundryMgmt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Main class that starts the laundry management application.
 *
 * @author David PETREL
 * @version 1.0
 * @since 2023-11-16
 */
@SpringBootApplication
public class LaundryMgmtApplication {

    private static final Logger logger = LogManager.getLogger(LaundryMgmtApplication.class);

    /**
     * Flag to identify machine API related records
     */
    public static Marker LogToMachinesLogFile = MarkerManager.getMarker("LogToMachinesLogFile");

    /**
     * main method that starts the application
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LaundryMgmtApplication.class, args);
        logger.info(LogToMachinesLogFile, "This log contains the basic log information of requests made to server on the machines API");
    }
}
