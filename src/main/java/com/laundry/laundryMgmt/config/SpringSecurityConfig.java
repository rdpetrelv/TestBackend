// © 2024 Laundary Management
// This file is under the MIT License.
// See the LICENSE file at the root of the project for more details.

package com.laundry.laundryMgmt.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;



import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author  Fatima GHAOUI
 * Configuration class responsible for setting up Spring Security configurations.
 */
@Configuration
public class SpringSecurityConfig {

    /**
     * Constant representing the role 'USER'.
     */
    public static final String ROLE_USER = "USER";

    /**
     * Creates and configures a UserDetailsService for handling user details.
     *
     * @return A UserDetailsService with an in-memory user 'user' having a password 'myPassword' and role 'USER'.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
        );
        return manager;
    }

    /**
     * Configures the default security filter chain using HttpSecurity.
     *
     * @param http HttpSecurity instance to configure security settings.
     * @return A SecurityFilterChain configured with authentication for all requests and default login options.
     * @throws Exception If there's an issue while configuring security settings.
     */
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}