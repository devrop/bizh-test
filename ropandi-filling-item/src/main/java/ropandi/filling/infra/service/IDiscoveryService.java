package ropandi.filling.infra.service;

import reactor.core.publisher.Flux;

public interface IDiscoveryService {

	
	Flux<String> serviceAddressFor(String service);
}
