package com.smartcv.smartcv.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(auth -> {
                    // Rotas públicas
                    auth.requestMatchers(
                            "/",
                            "/SmartCV",
                            "/SmartCV/login",
                            "/SmartCV/signUp",
                            "/SmartCV/userProfile",
                            "/favicon.ico",
                            "/SmartCV/terms&Conditions",
                            "/SmartCV/error",
                            "/start/**",
                            "/personal_info/**",
                            "/signUp/**",
                            "/education/**",
                            "/login/**",
                            "/profile/**",
                            "/terms&Conditions/**",
                            "/SmartCV/profiles",
                            "/SmartCV/profile",
                            "/SmartCV/education",
                            "/https://c30b-2804-1b3-a280-8bfb-a9fc-dd1c-983d-bbfe.ngrok-free.app",
                            "https://c30b-2804-1b3-a280-8bfb-a9fc-dd1c-983d-bbfe.ngrok-free.app/SmartCV/**",
                            "/SmartCV/oauth2/authorization/google",
                            "/SmartCV/oauth2/**",  // Adicione isso
                            "/SmartCV/login/oauth2/**",  // Adicione isso
                            "/SmartCV/personalInfo"
                    ).permitAll();
                    // Bloqueia explicitamente o acesso à rota específica com um parâmetro ID no perfil
                    auth.requestMatchers("/profile?id=**").denyAll();

                    // Exige autenticação para qualquer outra rota não explicitamente configurada
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(login -> {
                    login.userInfoEndpoint(userInfo ->
                            userInfo.userService(new DefaultOAuth2UserService())
                    )
                    // Configura o login via OAuth2, usando uma página específica para autorização http://localhost:8080/oauth2/authorization/google
                            .loginPage("/oauth2/authorization/google") // Define a URL da página de login do Google OAuth2
                            .successHandler((request, response, authentication) ->{
                                // Evita tokens e utiliza sessão após sucesso no login
                                if (authentication instanceof OAuth2AuthenticationToken) {
                                    System.out.println("Login deu bom");
                                    OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
                                    request.getSession().setAttribute("user", auth2User);
                                    response.sendRedirect("/SmartCV");// Redireciona após login

                                }
                        });
                }).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .csrf(AbstractHttpConfigurer::disable)
                .build(); // Constrói e retorna o SecurityFilterChain configurado
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}