package org.example.library.mapper;

import org.example.library.dto.AuthUserDto;
import org.example.library.dto.UserRegistrationDto;
import org.example.library.model.Role;
import org.example.library.model.RoleName;
import org.example.library.model.User;
import org.example.library.service.RoleService;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public abstract class UserMapper {
    @Autowired
    private RoleService roleService;

    @Mapping(target = "roles", expression = "java(getUserRoleSet())")
    public abstract User fromUserRegistrationDtoToUserWithRoleUser(UserRegistrationDto userRegistrationDto);

    public abstract AuthUserDto fromUserToAuthUserDto(User user);

    protected Set<Role> getUserRoleSet() {
        Role role = roleService.findByName(RoleName.ROLE_USER);
        Set<Role> result = new HashSet<>();
        result.add(role);
        return result;
    }
}
