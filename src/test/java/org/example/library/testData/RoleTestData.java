package org.example.library.testData;

import org.example.library.model.Role;
import org.example.library.model.RoleName;

import java.util.Set;

public final class RoleTestData {

    public static final Long ROLE_USER_ID = 1L;
    public static final RoleName ROLE_USER_ROLE_NAME = RoleName.ROLE_USER;


    private RoleTestData() {
        throw new UnsupportedOperationException("Cannot create an instance");
    }

    public static Role createRoleUserRole() {
        return Role.builder()
                .withId(ROLE_USER_ID)
                .withName(ROLE_USER_ROLE_NAME)
                .build();
    }

    public static Set<Role> createRoleSetWithUserRole() {
        return Set.of(createRoleUserRole());
    }
}
