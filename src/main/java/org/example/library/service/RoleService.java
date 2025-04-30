package org.example.library.service;

import org.example.library.model.Role;
import org.example.library.model.RoleName;

public interface RoleService {
    Role findByName(RoleName name);
}
