package org.sivaiitroracle9.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {

	@Autowired
	public RoleRepository roleRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public BlogRepository blogRepository;

	@Autowired
	public ItemRepository itemRepository;

	@PostConstruct
	public void init() {

/*		DatabaseManagerSwing.main(new String[] { "--url",
				"jdbc:hsqldb:mem:dataSource", "--user", "sa", "--password", "" });*/

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setEnabled(true);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
		roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		Blog blogJavavids = new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);

/*		Item item1 = new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("http://www.javavids.com");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);*/

	}

}
