package com.github.account_service;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients("com.github.account_service.client")
public class AccountServiceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountServiceApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
