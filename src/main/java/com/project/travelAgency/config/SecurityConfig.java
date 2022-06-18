package com.project.travelAgency.config;

import com.project.travelAgency.model.entity.Role;
import com.project.travelAgency.service.UserService;
import com.project.travelAgency.util.PassUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;


    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(PassUtil.passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider myAuthProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(PassUtil.passwordEncoder());
        auth.setUserDetailsService(userService);
        return auth;
    }


    // метод определяет какой урл нужно защищать, какой не нужно
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users", "/tours/showCreateTour").hasAnyAuthority(Role.ADMIN.name())// просматривать пользователй могут только пользователи с правами ADMIN
                .antMatchers("/users/new", "/deleteTour", "/editTour").hasAuthority(Role.ADMIN.name()) //создавать пользователя могут только пользователи с правами ADMIN
                .anyRequest().permitAll() //остальные запросы для всех
                .and()
                .formLogin()// страницы логина и аутинтификации разрешены
                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .failureUrl("/login-error")
                .permitAll() //для всех
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //разъединение
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID") // если успешно, переходим в корень и удаляем куки
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();
    }
}
