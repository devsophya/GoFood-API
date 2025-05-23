    package br.com.gofood.gofood.security;

    import br.com.gofood.gofood.security.SecurityAddressFilter;
    import br.com.gofood.gofood.security.SecurityClientFilter;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

    @AllArgsConstructor
    @Configuration
    @EnableMethodSecurity
    public class SecurityConfig {

        private final br.com.gofood.gofood.security.SecurityFilter securityFilter;
        private final SecurityClientFilter securityClientFilter;
        private final br.com.gofood.gofood.security.SecurityMenuFilter securityMenuFilter;
        private final SecurityAddressFilter securityAddressFilter;
        private final br.com.gofood.gofood.security.SecurityOrderFilter securityOrderFilter;
        private final br.com.gofood.gofood.security.SecurityPromotionFilter securityPromotionFilter;

        private static final String[] SWAGGER_LIST = {
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**"
        };

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/restaurant/auth/**").permitAll();
                        auth.requestMatchers("/client/auth/**").permitAll();
                        auth.requestMatchers("/ws/**", "/ws-orders/**").permitAll();
                        auth.requestMatchers("/addresses/**").authenticated();
                        auth.requestMatchers("/menus/**").authenticated();
                        auth.requestMatchers("/orders/**").authenticated();
                        auth.requestMatchers("/promotion/**").authenticated();
                        auth.requestMatchers(SWAGGER_LIST).permitAll();
                        auth.anyRequest().permitAll();
                    })
                    .addFilterBefore(securityPromotionFilter,BasicAuthenticationFilter.class)
                    .addFilterBefore(securityOrderFilter, BasicAuthenticationFilter.class)
                    .addFilterBefore(securityAddressFilter, BasicAuthenticationFilter.class)
                    .addFilterBefore(securityMenuFilter, BasicAuthenticationFilter.class)
                    .addFilterBefore(securityClientFilter, BasicAuthenticationFilter.class)
                    .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }
    //S