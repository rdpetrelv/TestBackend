/** © 2024 Laundary Management
 * This file is under the MIT License.
 * See the LICENSE file at the root of the project for more details.
*/

package com.laundry.laundryMgmt;

import com.laundry.laundryMgmt.businessLogic.MachineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Main configuration for the laundry management application
 *
 * @author David PETREL
 * @version 1.0
 */
@Configuration
public class LaundryMgmtApplicationConfig {
    /**
     * Configures and runs a task to set the washing time using the machine service.
     * @param machineService set the washing time
     * @return instance that executes the washing time setup
     */
    @Bean
    public CommandLineRunner washingTimeCommandLine(MachineService machineService){
        return args -> {
            // Sets the washing time with specific values.
            machineService.washingTime(55,65);
        };
    }
}
