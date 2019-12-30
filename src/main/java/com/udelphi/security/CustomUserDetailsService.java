package com.udelphi.security;

import java.util.Collection;
import java.util.Optional;
import com.udelphi.model.User;
import com.udelphi.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));

        return new SecurityUser(user.getEmail(), user.getPassword(),
                getAuthorities(user), user.getId());

    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles()
                .stream()
                .map(role -> "ROLE_" + role.getName())
                .toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(userRoles);
    }

}
