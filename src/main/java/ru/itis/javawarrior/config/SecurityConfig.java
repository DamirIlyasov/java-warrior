package ru.itis.javawarrior.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userInfoTokenServices")
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/login**", "/webjars/**", "/error**", "/swagger-ui**", "/swagger-resources/**", "/v2**")
                .permitAll()
                .antMatchers("/compile**", "/user**", "/level**")
                .authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ApiTokenAccessFilter(tokenServices), AbstractPreAuthenticatedProcessingFilter.class);
    }

}
