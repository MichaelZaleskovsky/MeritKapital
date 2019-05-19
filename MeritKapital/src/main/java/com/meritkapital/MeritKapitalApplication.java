package com.meritkapital;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeritKapitalApplication {
	public static final String AMERICAN = "AMERICAN";
	public static final String EUROPEAN = "EUROPEAN";
	public static final long MS_PER_DAY = 24*60*60*1000;
	public static final int SPOT_PERIOD = 2;
	public static final int FORWARD_PERIOD = 5;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MeritKapitalApplication.class, args);
	}

}
