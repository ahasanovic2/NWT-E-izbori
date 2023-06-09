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
                .requestMatchers(POST, "/elections/create-election").hasRole("ADMIN")
                .requestMatchers(POST, "/elections/election/set-pollingstations").hasRole("ADMIN")
                .requestMatchers(GET, "/elections/election/pollingstations").hasRole("ADMIN")
                .requestMatchers(POST, "/elections/election/add-lists").hasRole("ADMIN")
                .requestMatchers(GET, "/elections/election/lists").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET, "/elections/election/candidates").hasAnyRole("USER","ADMIN")
                .requestMatchers(POST, "/elections/election/add-candidates").hasRole("ADMIN")
                .requestMatchers(GET,"/elections/get-elections-for-user").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/election/get-lists").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/election/candidates").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/election/get-id").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/candidate/get-id").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/candidate/get-name").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/list/get-id").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/list/get-name").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/get-candidate-by-name").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/get-list-by-name").hasAnyRole("USER","ADMIN")
                .requestMatchers(GET,"/elections/get-election-by-id").hasAnyRole("USER","ADMIN")
                .requestMatchers("/elections/**").denyAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

