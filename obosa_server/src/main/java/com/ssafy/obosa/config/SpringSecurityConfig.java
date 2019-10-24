package com.ssafy.obosa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable();
        http.csrf().ignoringAntMatchers("/signup", "/auth/login");

        // comment out for develop
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST, "/signup").permitAll()
//                .antMatchers(HttpMethod.GET, "/signup/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated();
//                .and()
//                // We filter the api/login requests
//                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
//                // And filter other requests to check the presence of JWT in header
//                .addFilterBefore(new JWTAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // System.out.println("Login detail::"+auth.toString());
        //  System.out.println("called2");
//        auth.userDetailsService(userDetailService);

        // Create a default account
	   /* auth.inMemoryAuthentication()
	        .withUser("admin")
	        .password("$2a$04$zgOtqbRANAuzI6XmymBpbO3bwhFAtyG1KPqEcLps5b.GscmtlqIG6")
	        .roles("ADMIN");*/
    }
}