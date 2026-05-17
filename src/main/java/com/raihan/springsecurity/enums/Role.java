package com.raihan.springsecurity.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author Raihan-89
 */
@Getter
public enum Role {
    ADMIN(Set.of(Permissions.READ, Permissions.WRITE, Permissions.DELETE)),
    USER(Set.of(Permissions.READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
