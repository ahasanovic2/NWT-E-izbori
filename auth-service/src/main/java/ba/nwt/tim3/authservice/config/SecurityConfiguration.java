package ba.nwt.tim3.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static ba.nwt.tim3.authservice.user.Role.ADMIN;
import static ba.nwt.tim3.authservice.user.Role.USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/authentication/**").permitAll()
                .requestMatchers(POST, "/pollingStations/create").hasRole(ADMIN.name())
                .requestMatchers(GET, "/pollingStations").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(GET, "/pollingStations/user").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(GET, "/pollingStations/user/get-by-id").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers(GET, "/pollingStations/get-by-name").hasAnyRole(ADMIN.name(), USER.name())
                .requestMatchers("/pollingStations/**").denyAll()
                .requestMatchers(GET, "/users").hasRole(ADMIN.name())
                .requestMatchers(POST, "/users/{userId}/pollingStation/{pollingStationId}").hasRole(USER.name())
                .requestMatchers(POST, "/users/{userId}/pollingStation").hasRole(ADMIN.name())
                .requestMatchers(POST, "/users/pollingStation/{pollingStationId}").hasRole(USER.name())
                .requestMatchers(POST, "/users/pollingStation").hasRole(USER.name())
                .requestMatchers(GET,"/users/id").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers("/users/**").denyAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/authentication/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
