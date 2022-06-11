package ropandi.box.oauth.service;

import reactor.core.publisher.Mono;
import ropandi.box.oauth.AccessToken;
import ropandi.box.oauth.Credentials;

public interface ITokenService {

	public Mono<AccessToken> token(Credentials credentials);
}
