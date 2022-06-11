package ropandi.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;

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
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(this.credentialUserDetails).passwordEncoder(this.passwordEncoder);

	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/login", "/**/register/**","/oauth/token/**").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().permitAll();
	  }
	  
	   @Bean
	    public AuthenticationManager authenticationManagerBean()
	        throws Exception {
	        return super.authenticationManagerBean();
	    }
	   /*
	   @Bean
	    public UserDetailsService users() {
	        
	        
	        UserDetails user = User.withUsername("arop")
	                .password(passwordEncoder.encode("1234"))
	                .roles("USER")
	                .build();
	        List<UserDetails> users = new ArrayList<>();
	        users.add(user);
	        return new InMemoryUserDetailsManager(users);
	        
	    }
	    */
	   @Bean
	   public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
		   return new InMemoryUserDetailsManager();
	   }
	   
	   
}
