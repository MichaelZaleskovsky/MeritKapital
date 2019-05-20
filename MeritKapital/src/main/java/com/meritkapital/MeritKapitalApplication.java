package com.meritkapital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MeritKapitalApplication {
	public static final String AMERICAN = "AMERICAN";
	public static final String EUROPEAN = "EUROPEAN";
	public static final long MS_PER_DAY = TimeUnit.DAYS.toMillis(1);
	public static final int SPOT_PERIOD = 2;
	public static final int FORWARD_PERIOD = 5;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MeritKapitalApplication.class, args);
	}

}
