package com.application.publishers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.application.publishers.service.MyUserDetailsService;

import javax.sql.DataSource;
import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private DataSource datasource;

	@Bean
	public MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(myUserDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	
	@Bean
	protected  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
		 .csrf().disable()
		 .authorizeHttpRequests()
		 .requestMatchers("/authenticate","/ADMIN").permitAll()
//		 .requestMatchers("/admins","/createAdmin","/createPublisher").hasAuthority("PUBLISHER")
//		 .requestMatchers("/delete-admin/**","/delete-publisher/**").hasAuthority("ADMIN")
//		 .requestMatchers("/{user_id}/role/{role_id}","/{user_id}/publisher/{role_id}").hasAnyAuthority("ADMIN","PUBLISHER")
		 .anyRequest().authenticated()
		 .and()
		   .formLogin()
		   .and()
		   .rememberMe().tokenRepository(persistentTokenRepository())
		   // .tokenValiditySeconds(1 * 1 * 1 * 1) // expiration time: 7 days
//		    .key("AbcdefghiJklmNoPqRstUvXyz")   // cookies will survive if restarted
		   .and()
          .httpBasic();
//		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 return  http.build();
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	JdbcTokenRepositoryImpl tokenRepository=new  JdbcTokenRepositoryImpl();
	tokenRepository.setDataSource(datasource);
		return tokenRepository;
	}
	
}

	
