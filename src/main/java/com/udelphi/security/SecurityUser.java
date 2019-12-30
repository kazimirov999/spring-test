package com.udelphi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.joining;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private static final Log logger = LogFactory.getLog(SecurityUser.class);

    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Integer id;

    public SecurityUser(String username, String password,
                        Collection<? extends GrantedAuthority> authorities, Integer id) {
        this(username, password, true, true, true, true, authorities, id);
    }


    public SecurityUser(String username, String password, boolean enabled,
                        boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Integer id) {

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = new HashSet<>(authorities);
        this.id = id;
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SecurityUser) {
            return username.equals(((SecurityUser) rhs).username);
        }
        return false;
    }


    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {

        return super.toString() + ": " +
                "Username: " + this.username + "; " +
                "Password: [PROTECTED]; " +
                "Enabled: " + this.enabled + "; " +
                "AccountNonExpired: " + this.accountNonExpired + "; " +
                "credentialsNonExpired: " + this.credentialsNonExpired + "; " +
                "AccountNonLocked: " + this.accountNonLocked + "; " +
                "Granted Authorities: " +
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(joining(",")) +
                ";";
    }
}
