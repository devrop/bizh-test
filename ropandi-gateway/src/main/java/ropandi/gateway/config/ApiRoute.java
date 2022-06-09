package ropandi.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRoute {

	
	@Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
       return builder.routes()
      .route("paymentId", r->r.path("/api/payment/**").uri("http://localhost:9009")) //static routing
      .route("orderId", r->r.path("/api/order/**").uri("lb://ORDER-SERVICE")) //dynamic routing
      .build();
    }
}
