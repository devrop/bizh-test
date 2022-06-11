package ropandi.slot.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ropandi.slot.oauth.Credentials;
import ropandi.slot.infra.service.DiscoveryService;
import ropandi.slot.oauth.service.ITokenService;
import ropandi.slot.share.model.BoxModel;
@Service
public class BoxService implements IBoxService {

	@Autowired
	private WebClient webClient;
	@Autowired
	private DiscoveryService discoveryService;
	@Value("${boxs.service}")
	private String boxService;
	/*
	
	@Autowired
	private ITokenService tokenService;
	@Value("${boxs.oauth.client_id}")
	private String boxClientId;
	@Value("${boxs.oauth.client_secret}")
	private String boxSecretId;
	*/
	@Override
	public Mono<BoxModel> findOneBox(Long id) {
		// TODO Auto-generated method stub
		/*
		Credentials credentials = Credentials.builder()
				.clientId(boxClientId)
				.clientId(boxSecretId)
				.build();
				*/
		 return discoveryService.serviceAddressFor(this.boxService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/findById/"+ id).build().get()))
                 /*
		        .flatMap(requestHeadersUriSpec ->
	            Flux.combineLatest(Flux.just(requestHeadersUriSpec),Flux.from(tokenService.token(credentials)),(reqSpec, token) ->{
	              reqSpec.header("Authorization","Bearer" + token.getToken());
	              return reqSpec;
	            })
	                .next())
	                .
		        
		        */
		        .map(RequestHeadersSpec::retrieve)
		        .flatMap(eq -> eq.bodyToMono(BoxModel.class)
		        	)
		        ;
	}

	public Mono<BoxModel> buildEmpty(){
	       return Mono.just(BoxModel.builder()
	    		   .boxCode(0L).boxName("not exist").build()); //<-- evaluated as soon as read
	}
}
