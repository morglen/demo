package ru.diasoft.micro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.enabled:true}")
    private boolean securityEnabled;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (securityEnabled) {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers(getUrlArray()).permitAll()
                    .antMatchers("/**").authenticated();
        } else {
            http.authorizeRequests().antMatchers("/**").permitAll();
        }
    }

    private String[] getUrlArray() {
        List<String> list = new LinkedList<>();
        list.add("/");
        list.add("/csrf");
        list.add("/v3/api-docs");
        list.add("/swagger-resources/configuration/ui");
        list.add("/configuration/ui");
        list.add("/swagger-resources");
        list.add("/swagger-resources/configuration/security");
        list.add("/configuration/security");
        list.add("/swagger-ui.html");
        list.add("/swagger-ui/**");
        list.add("/webjars/**");
        list.add("/actuator/**");
        return list.toArray(new String[0]);
    }
}