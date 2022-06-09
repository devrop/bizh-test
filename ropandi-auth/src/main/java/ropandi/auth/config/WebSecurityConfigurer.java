package ropandi.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import ropandi.auth.service.impl.CredentialsDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  @Autowired
	  private CredentialsDetailsService credentialUserDetails;
	
	 @Override
	  @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(this.credentialUserDetails).passwordEncoder(this.passwordEncoder);
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/login", "/**/register/**").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().permitAll();
	  }
	  
	   @Bean
	    public AuthenticationManager authenticationManagerBean()
	        throws Exception {
	        return super.authenticationManagerBean();
	    }
}