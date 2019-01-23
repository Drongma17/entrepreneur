package com.thonglam.spsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/svc/v1/private/accounts/*").hasRole(USER)
                .antMatchers("svc/v1/private/ac/admin/**").hasRole(ADMIN)
                .and()
                .formLogin();

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
         auth.inMemoryAuthentication()
                 .withUser("user").password("password").roles(USER)
                 .and()
                 .withUser("yeshi").password("password").roles(USER, ADMIN);




    }

}
