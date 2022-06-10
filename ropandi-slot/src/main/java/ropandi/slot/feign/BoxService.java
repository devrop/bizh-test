package ropandi.slot.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import reactor.core.publisher.Mono;

import ropandi.slot.infra.service.DiscoveryService;
import ropandi.slot.share.model.BoxModel;

public class BoxService implements IBoxService {

	@Autowired
	private WebClient webClient;
	@Autowired
	private DiscoveryService discoveryService;
	@Value("${boxs.service}")
	private String boxService;
	@Override
	public Mono<BoxModel> findOneBox(Long id) {
		// TODO Auto-generated method stub
		 return discoveryService.serviceAddressFor(this.boxService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/findById/"+ id).build().get()))
		        .map(RequestHeadersSpec::retrieve)
		        .flatMap(eq -> eq.bodyToMono(BoxModel.class)
		        	)
		        ;
	}

}
