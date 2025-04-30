package org.example.library.mapper;

import org.example.library.dto.AuthUserDto;
import org.example.library.dto.UserRegistrationDto;
import org.example.library.model.User;
import org.example.library.service.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.example.library.testData.RoleTestData.ROLE_USER_ROLE_NAME;
import static org.example.library.testData.RoleTestData.createRoleUserRole;
import static org.example.library.testData.UserTestData.createUserRegistrationDto;
import static org.example.library.testData.UserTestData.createUserWithId1L;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @Mock
    private RoleServiceImpl roleService;

    @InjectMocks
    private UserMapperImpl userMapper;

    @Test
    public void fromUserRegistrationDtoToUser() {
        UserRegistrationDto dto =  createUserRegistrationDto();

        when(roleService.findByName(ROLE_USER_ROLE_NAME)).thenReturn(createRoleUserRole());

        User user = userMapper.fromUserRegistrationDtoToUserWithRoleUser(dto);

        assertNotNull(user);
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getPassword(), user.getPassword());
    }

    @Test
    public void fromUserToAuthUserDto() {
        User user = createUserWithId1L();
        AuthUserDto dto = userMapper.fromUserToAuthUserDto(user);

        assertNotNull(dto);
        assertEquals(user.getEmail(), dto.getEmail());
    }

}