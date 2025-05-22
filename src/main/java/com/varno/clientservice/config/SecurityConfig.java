package com.varno.clientservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                httpBasic(Customizer.withDefaults())


                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
        return username -> jdbcTemplate.query("select * from users where email=?",
                (rs, i) -> User.builder()
                        .username(rs.getString("email"))
                        .password(rs.getString("password"))
                        .authorities(
                                jdbcTemplate.query("select * from user_authority where id=?",
                                        (resultSet, j) ->
                                                new SimpleGrantedAuthority(resultSet.getString("authority")),
                                        rs.getInt("id"))
                        )
                        .build(), username).stream().findFirst().orElse(null);
    }
}
