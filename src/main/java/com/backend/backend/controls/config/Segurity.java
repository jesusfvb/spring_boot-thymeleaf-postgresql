package com.backend.backend.controls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Segurity extends WebSecurityConfigurerAdapter {
    String[] recursos = new String[] { "/Img/**", "/Css/**" };

    @Autowired
    private UserDetailsI userDetail;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(recursos).permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login").usernameParameter("u")
                .passwordParameter("p").and().csrf().disable().logout().permitAll().logoutSuccessUrl("/login");
    }
}