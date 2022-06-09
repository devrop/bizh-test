package ropandi.slot.oauth.service;

import reactor.core.publisher.Flux;

public interface IDiscoverService {

	Flux<String> serviceAddressFor(String service);
}
