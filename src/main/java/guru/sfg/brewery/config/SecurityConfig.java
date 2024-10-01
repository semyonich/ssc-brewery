package guru.sfg.brewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .password("{bcrypt}$2a$10$DmaKiYYP.XvumHF56w2SeObh/X4.wXA3t7gNxVxqdLWIESSLEd2bu")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{sha256}89f6026e1809ddfa2f15b222142b10ad775de9a06470578bf42d81387e547464e8477c1fec5f79f4")
                .roles("USER")
                .and()
                .withUser("scott")
                .password("{ldap}{SSHA}hpnhzv7Mmiw61WrtHBimnds/R7mtMi8yD0TcRw==")
                .roles("CUSTOMER");
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
