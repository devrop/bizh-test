package ropandi.slot.oauth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import ropandi.slot.oauth.model.AccessToken;
import ropandi.slot.oauth.model.Credentials;
@Service
public class TokenService implements ITokenService {

	
	  private WebClient webClient;

	  private String authService;

	  private  String authServiceApiPath;

	  private  DiscoveryService discoveryService;
	@Override
	public AccessToken token(Credentials credentials) {
		// TODO Auto-generated method stub
		final String authorizationHeader = Base64Utils.encodeToString((credentials.getClientId() + ":" + credentials.getClientSecret()).getBytes());
	     
		AccessToken token = null;
		discoveryService.serviceAddressFor(this.authService).next().flatMap(address ->
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
	    ).bodyToMono(AccessToken.class)).subscribe(c-> c).
		
	     return null;
	}

}
