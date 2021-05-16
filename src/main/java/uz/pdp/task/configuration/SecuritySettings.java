package uz.pdp.task.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.task.jwt.JWTFilter;
import uz.pdp.task.service.AuthDetailImpl;

@EnableWebSecurity
public class SecuritySettings extends WebSecurityConfigurerAdapter {

    final AuthDetailImpl authDetailImpl;

    final JWTFilter jwtFilter;

    public SecuritySettings(@Lazy JWTFilter jwtFilter,
                          @Lazy AuthDetailImpl authDetailImpl) {
        this.jwtFilter = jwtFilter;
        this.authDetailImpl = authDetailImpl;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // completed
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authDetailImpl);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/login").permitAll()
                .anyRequest().authenticated();

        // SPRING SECURITYGA UsernamePasswordAuthenticationFilter.class DAN OLDIN jwtFilter NI ISHLATISHNI BUYURISH
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // SPRING SECURITYGA SESSIYAGA USHLAB QOLMASLIGI UCHUN
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
