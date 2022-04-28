package com.project.tvavelAgency.config;

import com.project.tvavelAgency.model.entity.Role;
import com.project.tvavelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Basic
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/new").hasAuthority(Role.ADMIN.name()) //создавать пользователя могут только пользователи с правами ADMIN
                .anyRequest().permitAll() //остальные запросы для всех
                .and()
                    .formLogin()// страницы логина и аутинтификации разрешены
                    .loginPage("/login")
                    .loginProcessingUrl("/auth")
                    .permitAll() //для всех
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //разъединение
                    .logoutSuccessUrl("/").deleteCookies("JSESSIONID") // если успешно, переходим в корень и удаляем куки
                    .invalidateHttpSession(true)
                .and()
                    .csrf().disable();
    }
}
