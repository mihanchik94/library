package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.library.model.Role;
import org.example.library.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.example.library.testData.RoleTestData.ROLE_USER_ROLE_NAME;
import static org.example.library.testData.RoleTestData.createRoleUserRole;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;



    @Test
    public void whenFindByNameAndRoleExistsThenReturnRole() {
        Role expected = createRoleUserRole();
        when(roleRepository.findByName(expected.getName())).thenReturn(Optional.of(expected));
        Role actual = roleService.findByName(expected.getName());
        assertEquals(expected, actual);
    }

    @Test
    public void whenFindByNameAndRoleNotExistThenThrowException() {
        when(roleRepository.findByName(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roleService.findByName(ROLE_USER_ROLE_NAME));
    }
}