package com.finalprojectandreeapasc.user.repository;

import com.finalprojectandreeapasc.user.model.ERole;
import com.finalprojectandreeapasc.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
