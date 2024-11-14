package com.smartcv.smartcv.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .requestMatchers(
                        "/",
                        "/SmartCV",
                        "/SmartCV/login",
                        "/SmartCV/profiles",
                        "/SmartCV/signUp",
                        "/SmartCV/profile",
                        "/SmartCV/error",
                        "/start/**",
                        "/profile/**",
                        "/login/**",
                        "/register/**"
                ).permitAll() // Permite acesso público a essas rotas
                /*.anyRequest().authenticated()  // Exige autenticação para outras rotas
                .and()
                .formLogin(form -> form
                        .loginProcessingUrl("/SmartCV/login") // URL onde o Spring Security processa o login
                        .loginPage("/SmartCV/login")  // URL da página de login personalizada
                        .permitAll()  // Permite acesso público à página de login
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginProcessingUrl("/SmartCV/oauth2/callback") // URL onde o Spring Security processa o OAuth2 login
                        .permitAll()  // Permite acesso público ao login OAuth2
                )*/;

        return httpSecurity.build();
    }


/*
    // Bean do ClientRegistrationRepository
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(googleClientRegistration());
    }

    // Bean do OAuth2AuthorizedClientRepository
    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new InMemoryOAuth2AuthorizedClientRepository();
    }

    // Definição do ClientRegistration para o Google OAuth2
    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("YOUR_GOOGLE_CLIENT_ID")
                .clientSecret("YOUR_GOOGLE_CLIENT_SECRET")
                .scope("profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
    }
*/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}