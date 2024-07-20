package com.technologyos.role.repositories;

import com.technologyos.role.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT t FROM Role t WHERE t.roleName = ?1")
    Optional<Role> findByRoleName(String roleName);

    @Query("SELECT r FROM Role r WHERE r.status = ?1")
    List<Role> findAllWithSpecificStatus(String status);

    @Transactional
    @Modifying
    @Query("UPDATE Role r set r.status = 'I' WHERE r.id = ?1")
    int updateRoleStatus(long id);
}
