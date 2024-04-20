package com.hwgi.plc.trafficlight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TrafficlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficlightApplication.class, args);
	}

}
