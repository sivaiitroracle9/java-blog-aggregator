package org.sivaiitroracle9.spring.repository;

import org.sivaiitroracle9.spring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
