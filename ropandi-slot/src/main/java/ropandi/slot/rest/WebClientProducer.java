package ropandi.slot.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientProducer {

	  @Bean
	    public WebClient webClient(){
	        return WebClient.create();
	    }
}
