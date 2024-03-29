/**
 * 
 */
package com.tbone.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbone.limitservice.bean.LimitConfiguration;

/**
 * @author Gaurav Kumar
 *
 */

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		// return new LimitConfiguration(1000, 1);
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}

}
