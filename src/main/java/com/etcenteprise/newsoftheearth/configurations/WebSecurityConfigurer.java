package com.etcenteprise.newsoftheearth.configurations;

import com.etcenteprise.newsoftheearth.filters.JwtRequestFilter;
import com.etcenteprise.newsoftheearth.listener.CustomLoginSuccessHandler;
import com.etcenteprise.newsoftheearth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userdetailsservice")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /**
         * for editor (ck editor) By default X-Frame-Options is set to denied, to
         * prevent click jacking attacks. To override this.
         */
            http
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        
            http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/news/**").permitAll()
                .antMatchers("/newsUpload/**").access("hasAuthority('ROLE_ADMIN')")
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .loginPage("/user/login")
                .failureUrl("/user/login?error=true")
                .successHandler(successHandler())
                //.loginProcessingUrl("/authUser")
                //.usernameParameter("auth_user_name")
                //.passwordParameter("auth_user_pass")
                .and()
                .logout().deleteCookies("SSID")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

            http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");
    }


}
