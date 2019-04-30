package org.greenwin.VLclient;

import org.greenwin.VLclient.config.ApplicationPropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.greenwin")
@EnableDiscoveryClient
public class VlClientApplication implements CommandLineRunner {

	@Autowired
	ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(VlClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("outsourced configuration test: " + applicationPropertiesConfiguration.getTest());
		logger.info("Logging system works!");
	}
}

