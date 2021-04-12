package com.sda.website.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests() //autorizeaza urmatoarele request-uri
                .antMatchers("/webjars/**", "/web/login", "/web/form-login") //pentru aceste linkuri
                .permitAll(); //modul in care sa autorizeze
        http.authorizeRequests()
                .anyRequest() //orice alte request-uri fata de cele definite anterior
                .authenticated(); //trebuie autentificate
        //http.httpBasic();
        http.formLogin()
                .loginPage("/web/form-login")
                .loginProcessingUrl("/web/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/web/index")
                .failureUrl("/web/form-login");
        http.logout().logoutUrl("/web/logout");
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("andrei").password(passwordEncoder.encode("abc"))
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("bogdan")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER")
//                .and()
//                .passwordEncoder(passwordEncoder);
        auth.jdbcAuthentication()
                .dataSource(this.dataSource)
                .passwordEncoder(passwordEncoder);
        System.out.println(passwordEncoder.encode("12345"));
    }

}
