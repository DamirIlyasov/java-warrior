package ru.itis.javawarrior.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userInfoTokenServices")
    @Autowired
    private ResourceServerTokenServices tokenServices;


    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // other public endpoints of your API may be appended to this array
            "/",
            "/login**",
            "/error**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .antMatchers("/compile**", "/user**", "/level**")
                .authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ApiTokenAccessFilter(tokenServices), AbstractPreAuthenticatedProcessingFilter.class);
    }
}
