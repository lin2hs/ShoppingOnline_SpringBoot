/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linhtd.demo.config;

import com.linhtd.demo.repository.UserRepository;
import com.linhtd.demo.security.JWTAuthenticationFilter;
import com.linhtd.demo.security.JWTAuthorizationFilter;
import com.linhtd.demo.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Linh
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//
//        // Mình comment phần dưới này vì chúng ta ko sử dụng DB nhé. Nếu các bạn sử dụng, bỏ comment và config query sao cho phù hợp. Các bạn có thể GG để tìm hiểu thêm
//        auth.jdbcAuthentication().dataSource(dataSource())
//                .usersByUsernameQuery("SELECT name, password, valid from users where username=?")
//                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");

    }

//    @Bean(name = "dataSource")
//    public DriverManagerDataSource dataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/ashopdb?characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("");
//
//        return driverManagerDataSource;
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/auth/user/login");

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/user/login", "/auth/user/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/user/product/cart").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.PUT, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/", "/static/**", "/**.{js,json,css}").permitAll()
                .anyRequest().authenticated()
                //                 Permit all request temporary
                //                .anyRequest().permitAll()
                //                .and()
                //                .logout().deleteCookies("remove").invalidateHttpSession(false).logoutUrl("/auth/user/logout")
                //                .logoutSuccessUrl(logoutSuccessUrl)
                .and()
                .addFilter(authenticationFilter)
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//            }
//        };
//    }
}
