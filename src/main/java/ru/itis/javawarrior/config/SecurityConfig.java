package ru.itis.javawarrior.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/login**", "/webjars/**", "/error**", "/swagger-ui**", "/swagger-resources/**")
                .permitAll()
                .antMatchers("/compile**", "/user**", "/level**")
                .authenticated()
                .anyRequest().authenticated();
    }
}
