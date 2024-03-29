package ba.nwt.tim3.config;

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
                .requestMatchers(GET,"/results").hasRole("ADMIN")
                .requestMatchers(GET,"/results/full-election").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/pollingStation").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/candidate").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/list").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/pollingStation/candidate").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/pollingStation/list").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/results/election/{election_id}/pollingStation/{pollingStationId}/candidate/{candidateId}/show").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/results/**").denyAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}