package com.belong.customer.configuration;

import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder().username("Joe1").password("Joe1").roles("USER").build();
        UserDetails admin1 = User.withDefaultPasswordEncoder().username("admin1").password("admin1").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, admin1);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers(String.valueOf(HttpMethod.PUT), "/api/v1/customers/**")
                .permitAll().anyRequest().authenticated()
                .and().csrf().disable();
    }
}
