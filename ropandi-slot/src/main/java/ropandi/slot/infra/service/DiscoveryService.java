package ropandi.slot.infra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class DiscoveryService implements IDiscoveryService {

	  @Autowired
	  private LoadBalancerClient lbClient;

	  @Autowired
	  private DiscoveryClient dClient;
	
	@Override
	public Flux<String> serviceAddressFor(String service) {
		// TODO Auto-generated method stub
		return Flux.defer(() ->  Flux.just(dClient.getInstances(service)).flatMap(srv ->
        Mono.just(lbClient.choose(service))
    ).flatMap(serviceInstance ->
        Mono.just(serviceInstance.getUri().toString())
    ));
	}

}
