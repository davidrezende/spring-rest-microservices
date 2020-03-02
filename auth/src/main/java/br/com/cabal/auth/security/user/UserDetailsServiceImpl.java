package br.com.cabal.auth.security.user;

import br.com.cabal.core.model.ApplicationUser;
import br.com.cabal.core.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Searching in the DB the user by username '{}'", username);
        saveH2ApplicationUserExample(username);
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        log.info("ApplicationUser found '{}'", applicationUser);
        if(applicationUser == null){
            throw new UsernameNotFoundException(String.format("Application user '%s' not found", username));
        }
        return new CustomUserDetails(applicationUser);
    }

    private void saveH2ApplicationUserExample(String username) {
        ApplicationUser applicationUser2 = applicationUserRepository.save(new ApplicationUser(1L, username, "$2a$10$UXLNwJz/hLd1y.VuRKtGVegRZolpCvn59AyeaQhDCGed7NergGjR2", "ADMIN"));
        if(applicationUser2 == null){
            throw new UsernameNotFoundException(String.format("Application user '%s' not add", username));
        }
    }

    private static final class CustomUserDetails extends  ApplicationUser implements  UserDetails{
        CustomUserDetails(@NotNull ApplicationUser applicationUser){
            super(applicationUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_");
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}


