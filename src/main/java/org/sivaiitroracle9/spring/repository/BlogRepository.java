package org.sivaiitroracle9.spring.repository;

import java.util.List;

import org.sivaiitroracle9.spring.entity.Blog;
import org.sivaiitroracle9.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
