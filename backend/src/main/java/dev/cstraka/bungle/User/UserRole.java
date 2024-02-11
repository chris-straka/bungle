package dev.cstraka.bungle.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * I only have the USER role so far 
 */
public enum UserRole {
    USER(Collections.emptySet());

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    private final Set<UserPermission> permissions;

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
