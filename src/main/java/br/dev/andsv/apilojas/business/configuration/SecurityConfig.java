package br.dev.andsv.apilojas.business.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/**")
                        .hasRole("RESPONSAVEL"))
                .httpBasic(Customizer.withDefaults())
//                Desabilito a proteção CSRF, conforme a recomendação existente em:
//                https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/csrf.html#when-to-use-csrf-protection
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        User.UserBuilder users = User.builder();
        UserDetails andsv = users
                .username("andsvb2")
                .password(passwordEncoder.encode("abc123"))
                .roles("RESPONSAVEL")
                .build();
        UserDetails tmpNaoEResponsavel = users
                .username("tmp-nao-e-responsavel")
                .password(passwordEncoder.encode("qrs456"))
                .roles("NAO-RESPONSAVEL")
                .build();
        UserDetails rpe = users
                .username("rpe")
                .password(passwordEncoder.encode("rpe789"))
                .roles("RESPONSAVEL")
                .build();
        return new InMemoryUserDetailsManager(andsv, tmpNaoEResponsavel, rpe);
    }
}
