package ropandi.box.feign;

import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import ropandi.box.infra.service.DiscoveryService;
import ropandi.box.share.model.BaseResponse;
import ropandi.box.share.model.RackModel;
import ropandi.box.share.model.RestResponse;

import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class RackService implements IRackService {

	@Autowired
	private WebClient webClient;
	@Autowired
	private DiscoveryService discoveryService;
	@Value("${racks.service}")
	private String rackService;
	
	@Override
	public Mono<RackModel> findRackById(Long rackCode) {
		// TODO Auto-generated method stub
		System.out.println("call rack service " + rackService);
		return discoveryService.serviceAddressFor(this.rackService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/findById/"+ rackCode).build().get()))
		        .map(RequestHeadersSpec::retrieve)
		        .flatMap(eq -> eq.bodyToMono(RackModel.class)
		        	).switchIfEmpty(buildEmpty())
		        ;
	}

	Mono<RackModel> buildEmpty(){
	       return Mono.just(RackModel.builder()
	    		   .rackCode(0L).rackName("not exist").build()); //<-- evaluated as soon as read
	}
	@Override
	public Mono<RackModel> findRackById(Set<Long> rackCodes) {
		// TODO Auto-generated method stub
		return null;
	}

}
