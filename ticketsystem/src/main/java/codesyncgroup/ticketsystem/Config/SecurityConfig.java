package codesyncgroup.ticketsystem.Config;

import codesyncgroup.ticketsystem.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    // Security filter configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable basic csrf config in order to create a new personalized security config
                .csrf(csrf ->
                        csrf
                                .disable())
                // Config that allows all requests that belong to the authentication controller and ask for auth to any request that doesn't have it
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()

                                .anyRequest().authenticated())
                // Config that Spring Security have to maintain the user info away from the server
                .sessionManagement(sessionManager ->
                        sessionManager
                                // 'Stateless' means that the server shouldn't create or use HTTP sessions to store user state
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // The security config uses the default auth provider config
                .authenticationProvider(authProvider)
                // Uses a custom filter created to add an extra security layer
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}