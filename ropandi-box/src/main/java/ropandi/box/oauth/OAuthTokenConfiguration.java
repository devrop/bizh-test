package ropandi.box.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthTokenConfiguration {

  @Value("${oauth.privateKey}")
  private String privateKey;


  @Bean
  public JwtTokenStore tokenStore() throws Exception {
    JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
    enhancer.setSigningKey(privateKey);
    enhancer.afterPropertiesSet();
    return new JwtTokenStore(enhancer);
  }

}
