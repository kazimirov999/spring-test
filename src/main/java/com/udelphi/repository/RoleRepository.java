package com.udelphi.repository;

import java.util.Set;
import com.udelphi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select u.roles from User u where u.id =:id")
    Set<Role> findAllByUserId(int id);
}
