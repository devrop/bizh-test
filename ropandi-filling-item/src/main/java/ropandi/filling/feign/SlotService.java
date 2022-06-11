package ropandi.filling.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ropandi.filling.oauth.service.ITokenService;
import ropandi.filling.oauth.Credentials;
import ropandi.filling.infra.service.DiscoveryService;
import ropandi.filling.share.model.SlotModel;
@Service
public class SlotService implements ISlotService {

	
	@Autowired
	private WebClient webClient;
	@Autowired
	private DiscoveryService discoveryService;
	@Value("${slots.service}")
	private String slotService;
	/*
	@Autowired
	private ITokenService tokenService;
	@Value("${slots.oauth.client_id}")
	private String slotClientId;
	@Value("${slots.oauth.client_secret}")
	private String slotSecretId;
	*/
	
	@Override
	public Mono<SlotModel> findOneSlotById(Long slotCode) {
		// TODO Auto-generated method stub
		/*System.out.println("slot Code " + slotCode);
		System.out.println("slotService " + slotService);
		Credentials credentials = Credentials.builder()
				.clientId(slotClientId)
				.clientId(slotSecretId)
				.build();*/
		return discoveryService.serviceAddressFor(this.slotService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/findById/"+ slotCode).build().get()))
		        /*.
		        flatMap(requestHeadersUriSpec ->
	            Flux.combineLatest(Flux.just(requestHeadersUriSpec),Flux.from(tokenService.token(credentials)),(reqSpec, token) ->{
	              reqSpec.header("Authorization","Bearer" + token.getToken());
	              return reqSpec;
	            })
	                .next())
                */
		        .map(RequestHeadersSpec::retrieve)
		        .flatMap(eq -> eq.bodyToMono(SlotModel.class)
		        	).switchIfEmpty(buildEmpty())
		        ;
	}

	Mono<SlotModel> buildEmpty(){
	       return Mono.just(SlotModel.builder()
	    		   .slotCode(0L).slotName("not exist").build()); //<-- evaluated as soon as read
	}
	@Override
	public Mono<SlotModel> updateCapacity(SlotModel slotCode) {
		// TODO Auto-generated method stub
		return discoveryService.serviceAddressFor(this.slotService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/updateCapacity")
		        		.build().post()
		        		.body(BodyInserters.fromObject(slotCode))
		        		))
		        		.map(RequestHeadersSpec::retrieve)
				        .flatMap(eq -> eq.bodyToMono(SlotModel.class));
	}

}
