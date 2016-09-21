package org.sivaiitroracle9.spring.repository;

import org.sivaiitroracle9.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

}
