package org.sivaiitroracle9.spring.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sivaiitroracle9.spring.entity.Item;
import org.sivaiitroracle9.spring.exception.RssException;

public class RssServiceTest {

	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService
				.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10, items.size());

		Item firstItem = items.get(0);
		assertEquals(
				"How to solve Source not found error during debug in Eclipse",
				firstItem.getTitle());

		for (Item fi : items) {
			System.out.println("item-title: " + fi.getTitle());
			System.out.println("item-description: " + fi.getDescription());
			System.out.println("item-link: " + fi.getLink());
			System.out.println("item-item: " + fi.getItem());
			System.out.println("*************************");
		}
	}

}
