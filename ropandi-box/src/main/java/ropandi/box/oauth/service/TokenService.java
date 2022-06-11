package ropandi.box.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import ropandi.box.infra.service.IDiscoveryService;
import ropandi.box.oauth.AccessToken;
import ropandi.box.oauth.Credentials;

@Service
public class TokenService implements ITokenService{

	@Autowired
	public IDiscoveryService discoveryService;
	@Autowired
	private WebClient webClient;
	@Value("${auth.service}")
	private String authService;
	@Value("${auth.path}")
	private String authServiceApiPath;
	@Override
	public Mono<AccessToken> token(Credentials credentials) {
		// TODO Auto-generated method stub
		final String authorizationHeader = Base64Utils.encodeToString((credentials.getClientId() + ":" + credentials.getClientSecret()).getBytes());
	    return discoveryService.serviceAddressFor(this.authService).next().flatMap(address ->
	        this.webClient.mutate().baseUrl(address + "/" + this.authServiceApiPath).build()
	        .post()
	        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	        .header("Authorization","Basic " + authorizationHeader)
	        .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
	        .retrieve()
	        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
	            Mono.error(new RuntimeException("Invalid call"))
	        ).onStatus(HttpStatus::is5xxServerError, clientResponse ->
	        Mono.error(new RuntimeException("Error on server"))
	    ).bodyToMono(AccessToken.class));
	}

}
