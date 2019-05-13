package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.MemberService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	MemberService ms;
	
	@Autowired
	DataSource ds;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage("/login")
		.loginPage("/").defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutUrl("/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
		// .usernameParameter("username").passwordParameter("password");
		http.authorizeRequests()
		.antMatchers("/")
		.permitAll();
		
		http.authorizeRequests()
		.antMatchers("/admin","/admin/*").hasRole("ADMIN")
		.antMatchers("/myInfo","/myInfo/*").authenticated();
		
		http.userDetailsService(ms);
	}
//	@Bean
//	PasswordEncoder encoding() {
//		return new PasswordEncoder() {
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				System.out.println("matches rawPassword:" + rawPassword + ", encodedPassword:" + encodedPassword);
//				return this.encode(rawPassword).equals(encodedPassword);
//			}
//
//			@Override
//			public String encode(CharSequence rawPassword) {
//				rawPassword = "E" + rawPassword;
//				return (String) rawPassword;
//			}
//		};
//	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
