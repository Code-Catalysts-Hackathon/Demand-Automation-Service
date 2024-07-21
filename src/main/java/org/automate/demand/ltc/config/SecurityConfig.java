package org.automate.demand.ltc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    final static String[] PUBLIC_PATHS = {
            "/",
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/**",
            "/api-docs/**"
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PUBLIC_PATHS);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity; enable in production
                .authorizeHttpRequests(auth ->auth
                .requestMatchers(PUBLIC_PATHS).permitAll().anyRequest().authenticated());// Public endpoints
        return http.build();

}
}
