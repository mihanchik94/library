package org.example.library.testData;

import org.example.library.dto.UserRegistrationDto;
import org.example.library.model.User;


import static org.example.library.testData.RoleTestData.createRoleSetWithUserRole;

public final class UserTestData {

    public static final Long USER_ID = 1L;
    public static final String USER_USERNAME = "Username";
    public static final String USER_EMAIL = "test@example.com";
    public static final String USER_PASSWORD = "password";

    private UserTestData() {
        throw new UnsupportedOperationException("Cannot create an instance");
    }

    public static UserRegistrationDto createUserRegistrationDto() {
        return UserRegistrationDto.builder()
                .withUsername(USER_USERNAME)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .build();
    }


    public static User createUserWithId1L() {
        return User.builder()
                .withId(USER_ID)
                .withUsername(USER_USERNAME)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .withRoles(createRoleSetWithUserRole())
                .build();
    }
}
