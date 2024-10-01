package guru.sfg.brewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth ->
                                           auth.antMatchers("/", "/webjars/**", "/resources/**").permitAll()
                                                   .antMatchers("/beers/find").permitAll()
                                                   .antMatchers(HttpMethod.GET, "/api/v1/beer/**").permitAll()
                                                   .mvcMatchers(HttpMethod.GET, "/api/v1/beerUpc/{upc}").permitAll())
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password("guru")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("5729c48b3e5ad317117449ae6bb678f9e5ad0c7009f3750018e143e8066698c3ae08de7a7ed551a0")
                .roles("USER")
                .and()
                .withUser("scott")
                .password("tiger")
                .roles("CUSTOMER");
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                                    .username("spring")
//                                    .password("guru")
//                                    .roles("ADMIN")
//                                    .build();
//        
//        UserDetails user = User.withDefaultPasswordEncoder()
//                                    .username("user")
//                                    .password("password")
//                                   .roles("USER")
//                                    .build();
//        
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
