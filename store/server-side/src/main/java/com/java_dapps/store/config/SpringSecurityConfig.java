package com.java_dapps.store.config;

import com.java_dapps.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Created by eslavov on 29-Mar-18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(getBCryptPasswordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login",  "/customer/**", "/item/add").permitAll()
//                .antMatchers("/item/{\\\\d+}/buy").hasRole("CUSTOMER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout").permitAll()
////                .and()
////                .exceptionHandling().accessDeniedPage("/unauthorized")
//                .and()
//                .csrf().disable()
//        ;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().and()
//                .authorizeRequests()
//                .antMatchers("/user", "/login", "/customer/register",
//                        "/item/for-sale", "/item/add").permitAll()
////                .anyRequest().authenticated()
//                .and()
//                .csrf()
//                .disable()
//                ;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // starts authorizing configurations
                .authorizeRequests()
                // ignoring the guest's urls "
                .antMatchers("/customer/register","/login","/logout", "item/for-purchase").permitAll()
                // authenticate all remaining URLS
                .anyRequest().fullyAuthenticated().and()
      /* "/logout" will log the user out by invalidating the HTTP Session,
       * cleaning up any {link rememberMe()} authentication that was configured, */
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .and()
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session on the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
