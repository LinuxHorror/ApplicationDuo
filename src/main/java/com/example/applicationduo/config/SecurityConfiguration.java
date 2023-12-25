package com.example.applicationduo.config;

import com.example.applicationduo.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    @Bean
    public SecurityFilterChain chainSecurity(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(customizer -> {
            customizer.requestMatchers(mvc.pattern("/store")).permitAll();
            customizer.requestMatchers(mvc.pattern("/login/**")).permitAll();
            customizer.requestMatchers(mvc.pattern("/registration/**")).permitAll();
            customizer.requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN");
            customizer.anyRequest().permitAll();
        });
        http.httpBasic(Customizer.withDefaults());

        http.formLogin(cust -> {
            cust.loginPage("/login");
            cust.loginProcessingUrl("/login");
            cust.usernameParameter("username");
            cust.passwordParameter("password");
            cust.successHandler((request, response, authentication) -> {
                response.sendRedirect("/admin");
            });
            cust.failureHandler((request, response, exception) -> {
                response.sendRedirect("/login");
            });
        });

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Lazy
    @Bean(name = "userNow")
    @Scope(scopeName = "singleton")
    public UserEntity currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return (UserEntity) principal;
    }
}
