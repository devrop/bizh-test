package ropandi.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthServer extends AuthorizationServerConfigurerAdapter {

  /**
   * One day in seconds for trusted apps
   */
  private static final int ONE_DAY = 86400;

  /**
   * Three hours in seconds
   */
  private static final int THREE_HOURS = 10800;

  @Autowired
  private  AuthenticationManager authenticationManager;
  @Autowired
  private TokenStore tokenStore;
  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

 

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
	  System.out.println("disini b1");
    endpoints.authenticationManager(this.authenticationManager).approvalStoreDisabled()
        .tokenStore(this.tokenStore).accessTokenConverter(this.jwtAccessTokenConverter);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
	  System.out.println("disini 8");
    oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients)throws Exception {
	  clients
        .inMemory()
        .withClient("jGjsbIwLzOimHoUAEamIaMqtmb6FXo") // slot microservice
        .secret("ClWi4JbmO8pJ5UnHGKfqKyIkTqf4zaZOQ6UcYgUYbDG6Xo3JK0")
        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit",
            "client_credentials")
        .authorities("operator")
        .scopes("trust")
        .accessTokenValiditySeconds(ONE_DAY)
        .and()
        .withClient("Me1DYrByT15BEKTPucRaA9rQV3TRre") // box microservice
        .secret("AkPEu1riMp6v7xcH2ANALrBECEayQTobOHeOdgZVpx3EbFNkuY")
        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit",
            "client_credentials")
        .authorities("operator")
        .scopes("trust")
        .accessTokenValiditySeconds(ONE_DAY)
        .and()
        .withClient("oldsuU1K1TutmCpFuYVj25GBfXkEcg") // rack microservice
        .secret("s1GtA6rAcns5dXQQXaNWV7GBNPtHALnS1vcZcRoxGwuf8TBotL")
        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit",
            "client_credentials")
        .authorities("operator")
        .scopes("trust")
        .accessTokenValiditySeconds(ONE_DAY)
        .and()
        .withClient("RYcZ68XqU9bnxuJef2QhQMoVXLrKBM") // fillong microservice
        .secret("Ddcbxa8M4vWDkw7PM2B4KsyMvdJ6LKanWNiYQ2ripuwp2Fy8R5")
        .authorizedGrantTypes("authorization_code", "refresh_token", "implicit",
            "client_credentials")
        .authorities("operator")
        .scopes("trust")
        .accessTokenValiditySeconds(ONE_DAY);
  }

}