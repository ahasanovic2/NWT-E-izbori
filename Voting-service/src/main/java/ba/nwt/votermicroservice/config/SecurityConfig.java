package ba.nwt.votermicroservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(GET,"/pollingStations").hasRole("ADMIN")
                .requestMatchers("/pollingStations/**").denyAll()
                .requestMatchers(GET,"/voting/elections").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/voting/election/get-lists").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/voting/election/list/get-candidates").hasAnyRole("ADMIN","USER")
                .requestMatchers(POST,"/voting/vote-for-candidate").hasAnyRole("ADMIN","USER")
                .requestMatchers(POST,"/voting/vote-for-list").hasAnyRole("ADMIN","USER")
                .requestMatchers(GET,"/voting/get-vote-by-election").hasAnyRole("ADMIN","USER")
                .requestMatchers("/voting/**").denyAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}