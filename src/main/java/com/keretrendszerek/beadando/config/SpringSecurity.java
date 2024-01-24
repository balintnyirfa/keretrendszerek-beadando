package com.keretrendszerek.beadando.config;

import com.keretrendszerek.beadando.controller.RecordController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("userPass"))
                .roles("USER")
                .build();
        UserDetails manager = User.withUsername("manager")
                .password(passwordEncoder().encode("userPass"))
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(admin, user, manager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/index/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/default").permitAll()
                        .requestMatchers("/listRecords/**").hasRole("USER")
                        .requestMatchers("/uploadRecord").hasRole("USER")
                        .requestMatchers("/saveRecord").hasRole("USER")
                        .requestMatchers("/showNewRecordForm").hasRole("USER")
                        .requestMatchers("/showFormForUpdate/*").hasRole("USER")
                        .requestMatchers("/updateRecord/*").hasRole("USER")
                        .requestMatchers("/deleteRecord/*").hasRole("USER")
                        .requestMatchers("/listUsers").hasRole("ADMIN")
                        .requestMatchers("/saveUser").hasRole("ADMIN")
                        .requestMatchers("/deleteUser/*").hasRole("ADMIN")
                        .requestMatchers("/updateUser").hasRole("ADMIN")
                        .requestMatchers("/showFormForUserUpdate/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/default")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
