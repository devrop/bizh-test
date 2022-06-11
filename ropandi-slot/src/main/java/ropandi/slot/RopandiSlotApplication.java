package ropandi.slot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
@EnableResourceServer
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class RopandiSlotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RopandiSlotApplication.class, args);
	}

}
