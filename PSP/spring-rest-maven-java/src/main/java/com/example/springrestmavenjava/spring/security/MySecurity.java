package com.example.springrestmavenjava.spring.security;


import com.example.springrestmavenjava.spring.errors.ControlErrores;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.annotation.ApplicationScope;

import java.security.Key;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = false,jsr250Enabled = true)
public class MySecurity {


    @Qualifier("delegatedAuthenticationEntryPoint")
    private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;


    private final JwtTokenFilter jwtTokenFilter;



    @Bean("JWT")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Key key() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http


                .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                                    //.requestMatchers("/verify").hasRole("ROLE_ADMIN")
                                    .anyRequest().authenticated()


                )
                .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer

                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(new ControlErrores())



                )
//                .accessDeniedHandler(new ControlErrores())
                .build();


//        http.authorizeHttpRequests()
//                .requestMatchers("/home","/register","/saveUser").permitAll()
//                .requestMatchers("/welcome").authenticated()
//                .requestMatchers("/admin").hasAuthority("Admin")
//                .requestMatchers("/mgr").hasAuthority("Manager")
//                .requestMatchers("/emp").hasAuthority("Employee")
//                .requestMatchers("/hr").hasAuthority("HR")
//                .requestMatchers("/common").hasAnyAuthority("Employeee,Manager,Admin")
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/welcome",true)
//
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/accessDenied")
//
//                .and()
//                .authenticationProvider(authenticationProvider());

//        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/","/api/users", "/api/**");
//    }


    @Bean
    public AuthenticationProvider authenticationProvider(BCryptPasswordEncoder encoder, UserDetailsService uds) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }


}
