package org.sivaiitroracle9.spring.service;

import java.util.List;

import org.sivaiitroracle9.spring.entity.Item;
import org.sivaiitroracle9.spring.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> getItems() {
		return itemRepository.findAll(
				new PageRequest(0, 20, Direction.DESC, "publishedDate"))
				.getContent();
	}

}
