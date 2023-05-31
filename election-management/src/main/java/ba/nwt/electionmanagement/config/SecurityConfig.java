package ba.nwt.electionmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(GET, "/elections").hasAnyRole("USER", "ADMIN")
                .requestMatchers(POST, "/elections/create").hasRole("ADMIN")
                .requestMatchers(POST, "/elections/{electionId}/pollingStations").hasRole("ADMIN")
                .requestMatchers(GET, "/elections/{electionId}/pollingStations").hasAnyRole("USER","ADMIN")
                .requestMatchers(POST, "/elections/{electionId}/add-lists").hasRole("ADMIN")
                .requestMatchers(GET, "/elections/{electionId}/lists").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET, "/elections/{electionId}/lists/{listId}/candidates").hasAnyRole("USER","ADMIN")
                .requestMatchers(POST, "/elections/{electionId}/lists/{listId}/add-candidates").hasRole("ADMIN")
                .requestMatchers("/elections/**").denyAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

