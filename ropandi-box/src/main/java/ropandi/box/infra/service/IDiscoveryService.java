package ropandi.box.infra.service;

import reactor.core.publisher.Flux;

public interface IDiscoveryService {

	
	Flux<String> serviceAddressFor(String service);
}
