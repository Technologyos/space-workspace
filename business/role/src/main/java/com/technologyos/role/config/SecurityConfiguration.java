package com.technologyos.role.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   @Value("${spring.cors.allowedHeader}")
   private String allowedHeader;

   @Value("${spring.cors.exposedHeader}")
   private String exposedHeader;

   @Value("${spring.cors.allowedOrigin}")
   private String allowedOrigin;

   @Value("${spring.cors.allowedMethod}")
   private String allowedMethod;

   @Value("${spring.cors.allowedOriginPattern}")
   private String allowedOriginPattern;

   @Value("${spring.cors.maxAge}")
   private String maxAge;

   @Value("${spring.cors.registerConfigurationSource}")
   private String registerConfigurationSource;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.cors().configurationSource(createCorsConfigSource());
      http
         .csrf().disable()
         .authorizeHttpRequests()
         .anyRequest()
         .authenticated()
         .and()
         .httpBasic(withDefaults())
         .formLogin(withDefaults());
      return http.build();
   }

   public CorsConfigurationSource createCorsConfigSource(){
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedHeaders(Arrays.asList(allowedHeader.split(",")));
      config.setExposedHeaders(Arrays.asList(exposedHeader.split(",")));
      config.setAllowedOrigins(Arrays.asList(allowedOrigin.split(",")));
      config.setAllowedMethods(Arrays.asList(allowedMethod.split(",")));
      config.addAllowedOriginPattern(allowedOriginPattern);
      config.setMaxAge(Long.parseLong(maxAge));
      config.setAllowCredentials(Boolean.TRUE);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration(registerConfigurationSource, config);
      return source;
   }
}
