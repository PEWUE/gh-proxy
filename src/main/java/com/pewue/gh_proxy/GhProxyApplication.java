package com.pewue.gh_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GhProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhProxyApplication.class, args);
	}

}
