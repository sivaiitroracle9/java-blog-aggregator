package org.sivaiitroracle9.spring.repository;

import org.sivaiitroracle9.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
