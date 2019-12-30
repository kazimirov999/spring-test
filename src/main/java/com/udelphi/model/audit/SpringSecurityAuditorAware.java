package com.udelphi.model.audit;


import java.util.Optional;
import com.udelphi.security.SecurityUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(SecurityUser.class::cast)
                .map(SecurityUser::getUsername);
    }
}