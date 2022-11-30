package com.gemini.ims.security;

// Java Program to Illustrate Spring Security

// Importing required classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Annotation

@Configuration
// Class
public class SpringSecurityConfig
       {


  //  NOTE:  Spring Boot version lower than 2.7.0 or Spring Security version older than 5.7.1. then we
    // can use WebSecurityConfigurerAdapter class.

    // Annotation
//    @Override
//    protected void
//    configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("gfg")
//                .password(passwordEncoder().encode("pass"))
//                .roles("ADMIN")
//                .and()
//                .passwordEncoder(passwordEncoder())
//                .withUser("user")
//                .password(passwordEncoder().encode("pass"))
//                .roles("BASIC");
//    }
//
//    // Annotation
//    @Bean
//    // Method
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Annotation
//    @Override
//    // Method
//    protected void configure(HttpSecurity http)
//            throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/basic")
//                .hasAnyRole("BASIC", "ADMIN")
//                .antMatchers("/admin")
//                .hasRole("ADMIN")
//                .antMatchers("/")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .and()
//                .logout()
//                .logoutRequestMatcher(
//                        new AntPathRequestMatcher("/logout"))
//                .permitAll();
//    }


     @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("userPass"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("adminPass"))
                .roles("USER", "ADMIN")
                .build());

        return manager;
    }

           @Bean
           public PasswordEncoder passwordEncoder()
           {
               return new BCryptPasswordEncoder();
           }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                .hasRole("ADMIN")
                .antMatchers("/Book/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/Author/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/login/**")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}

