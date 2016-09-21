package org.sivaiitroracle9.spring.service;

import org.sivaiitroracle9.spring.entity.Blog;
import org.sivaiitroracle9.spring.entity.User;
import org.sivaiitroracle9.spring.repository.BlogRepository;
import org.sivaiitroracle9.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);

		blog.setUser(user);
		blogRepository.save(blog);
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
