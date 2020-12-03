package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("john").password(passwordEncoder.encode("1234")).roles("USER")
                .and()
                .withUser("anna").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeRequests()
                .antMatchers("/data-api/**").authenticated()
                .and().httpBasic()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        */
        http.authorizeRequests()
                .antMatchers("/emps/edit*").hasRole("ADMIN")
                .antMatchers("/emps/edit/**").hasRole("ADMIN")
                .antMatchers("/emps/save").hasRole("ADMIN")
                .antMatchers("/emps/delete*").hasRole("ADMIN")
                .antMatchers("/emps/delete/**").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/showAccessDenied");
    }
}
