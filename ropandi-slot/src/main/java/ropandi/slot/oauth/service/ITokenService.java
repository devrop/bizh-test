package ropandi.slot.oauth.service;

import ropandi.slot.oauth.model.AccessToken;
import ropandi.slot.oauth.model.Credentials;

public interface ITokenService {

	AccessToken token(Credentials credentials);
}
