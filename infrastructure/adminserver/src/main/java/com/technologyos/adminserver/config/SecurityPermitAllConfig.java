package com.technologyos.adminserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityPermitAllConfig {

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests( (auth) -> {
         try {
            auth.anyRequest().permitAll().and().csrf().disable();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }).httpBasic(withDefaults());
      return http.build();
   }
}
