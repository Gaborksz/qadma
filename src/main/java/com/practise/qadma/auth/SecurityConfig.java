package com.practise.qadma.auth;

import com.practise.qadma.auth.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            UserDetailsManager userDetailsManager) throws Exception {

        return http.authorizeHttpRequests(configurer -> {
                    configurer.requestMatchers("/api/auth/signin").permitAll();
                    configurer.requestMatchers("/api/auth/signup").permitAll();
                    configurer.requestMatchers("/api/auth/user-name").permitAll();
                    configurer.anyRequest().authenticated();
                })

                .addFilterBefore(new JWTFilter(authenticationManager(http, userDetailsManager)), BasicAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsManager userDetailsManager) throws Exception {

        AuthenticationManagerBuilder authMgrBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authMgrBuilder.userDetailsService(userDetailsManager).passwordEncoder(passwordEncoder());
        return authMgrBuilder.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
