package br.com.ebuybooks;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ebuybooks.security.ImplementsUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .formLogin()
	        	.loginPage("/login")
			.defaultSuccessUrl("/home", true) 
	        	.permitAll()
	        	.and()
	        .authorizeRequests()
	        .antMatchers("/cadastro").permitAll()
	        	.anyRequest()
	        	.authenticated();
    }
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder);
	}
    
    

}


