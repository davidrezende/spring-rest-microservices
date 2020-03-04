package br.com.cabal.auth.security.config;

import br.com.cabal.auth.security.filter.JwtUserNameAndPasswordAuthenticationFilter;
import br.com.cabal.core.property.JwtConfiguration;
import br.com.cabal.security.config.SecurityTokenConfig;
import br.com.cabal.security.token.creator.TokenCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityCredencialsConfig extends SecurityTokenConfig {
    private final UserDetailsService userDetailService;
    private final TokenCreator tokenCreator;

    public SecurityCredencialsConfig(JwtConfiguration jwtConfiguration, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailService, TokenCreator tokenCreator) {
        super(jwtConfiguration);
        this.userDetailService = userDetailService;
        this.tokenCreator = tokenCreator;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator));
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
