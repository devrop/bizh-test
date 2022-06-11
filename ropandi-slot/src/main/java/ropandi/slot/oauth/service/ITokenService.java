package ropandi.slot.oauth.service;

import reactor.core.publisher.Mono;
import ropandi.slot.oauth.AccessToken;
import ropandi.slot.oauth.Credentials;

public interface ITokenService {

	public Mono<AccessToken> token(Credentials credentials);
}
