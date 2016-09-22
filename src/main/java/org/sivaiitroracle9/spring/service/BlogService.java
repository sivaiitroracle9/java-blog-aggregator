package org.sivaiitroracle9.spring.service;

import java.util.List;

import org.sivaiitroracle9.spring.entity.Blog;
import org.sivaiitroracle9.spring.entity.Item;
import org.sivaiitroracle9.spring.entity.User;
import org.sivaiitroracle9.spring.exception.RssException;
import org.sivaiitroracle9.spring.repository.BlogRepository;
import org.sivaiitroracle9.spring.repository.ItemRepository;
import org.sivaiitroracle9.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RssService rssService;

	public void saveItems(Blog blog){
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for(Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if(savedItem == null) {
					System.out.println("item.title = " + item.getTitle());
					System.out.println("item.description = " + item.getDescription());
					System.out.println("item.link = " + item.getLink());
					item.setBlog(blog);
					itemRepository.save(item);	
				}
			}
		} catch (RssException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay=3600000)
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for(Blog blog: blogs){
			saveItems(blog);
		}
	}
	
	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);

		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		// TODO Auto-generated method stub
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		// TODO Auto-generated method stub
		return blogRepository.findOne(id);
	}

}
