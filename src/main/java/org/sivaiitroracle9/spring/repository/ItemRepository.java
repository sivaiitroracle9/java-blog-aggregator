package org.sivaiitroracle9.spring.repository;

import java.util.List;

import org.sivaiitroracle9.spring.entity.Blog;
import org.sivaiitroracle9.spring.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog, Pageable pageable);
}
