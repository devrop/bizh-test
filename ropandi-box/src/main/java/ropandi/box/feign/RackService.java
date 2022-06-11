package ropandi.box.feign;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ropandi.box.infra.service.DiscoveryService;
import ropandi.box.oauth.service.ITokenService;
import ropandi.box.share.model.RackModel;
import ropandi.box.oauth.Credentials;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class RackService implements IRackService {

	@Autowired
	private WebClient webClient;
	@Autowired
	private DiscoveryService discoveryService;
	@Value("${racks.service}")
	private String rackService;
	@Autowired
	private ITokenService tokenService;
	@Value("${racks.oauth.client_id}")
	private String rackClientId;
	@Value("${racks.oauth.client_secret}")
	private String rackSecretId;
	
	@Override
	public Mono<RackModel> findRackById(Long rackCode) {
		// TODO Auto-generated method stub
		
		Credentials credentials = Credentials.builder()
				.clientId(rackClientId)
				.clientSecret(rackSecretId)
				.build();
		System.out.println("id1 " +rackClientId );
		System.out.println("id1 " +rackSecretId );
		System.out.println("call rack service " + rackService);
		return discoveryService.serviceAddressFor(this.rackService).next()
		        .flatMap(address -> Mono.just(this.webClient
		        		.mutate()
		        		.baseUrl(address + "/findById/"+ rackCode).build().get()))
		        
		        .flatMap(requestHeadersUriSpec ->
	            Flux.combineLatest(Flux.just(requestHeadersUriSpec),Flux.from(tokenService.token(credentials)),(reqSpec, token) ->{
	              System.out.println("token "+token.getToken());
	            	reqSpec.header("Authorization","Bearer " + token.getToken());
	              return reqSpec;
	            })
	                .next())
		        
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
