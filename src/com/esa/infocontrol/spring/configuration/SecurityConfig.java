package com.esa.infocontrol.spring.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	static Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private @Qualifier("baseData") DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .rolePrefix("ROLE_")
            .getUserDetailsService().setEnableGroups(true);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/javax.faces.resource/**", "/resources/**").permitAll() 
            	.antMatchers("/WEB-INF/templates/**", "/signup", "/about").permitAll()
            	.antMatchers("/admin/**").hasRole("ADMIN")
            	.antMatchers("/**").hasRole("USER")
                .and()
            .formLogin()
            	.loginPage("/login.jsf") 
            	.permitAll()
            	.and()
            .logout()
            	.logoutUrl("/logout")
            	.logoutSuccessUrl("/login.jsf?logout")
            	.permitAll();
            
	}
	
	
	

}
