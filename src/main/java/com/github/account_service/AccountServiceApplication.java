package com.github.account_service;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableScheduling
@EnableRetry
@EnableFeignClients("com.github.account_service.client")
public class AccountServiceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(AccountServiceApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
