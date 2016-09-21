package org.sivaiitroracle9.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sivaiitroracle9.spring.entity.Blog;
import org.sivaiitroracle9.spring.entity.Item;
import org.sivaiitroracle9.spring.entity.Role;
import org.sivaiitroracle9.spring.entity.User;
import org.sivaiitroracle9.spring.repository.BlogRepository;
import org.sivaiitroracle9.spring.repository.ItemRepository;
import org.sivaiitroracle9.spring.repository.RoleRepository;
import org.sivaiitroracle9.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findOneWithBlogs(int id) {
		User user = userRepository.findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for (Blog blog : blogs) {
			System.out.println("User="+user.getName() + ", blog = "+ blog.getName());
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(
					0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		if(user!=null)
			return findOneWithBlogs(user.getId());
		return null;
	}

	public void save(User user) {
		user.setEnabled(true);
		user.setPassword((new BCryptPasswordEncoder()).encode(user
				.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);

		userRepository.save(user);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

}
