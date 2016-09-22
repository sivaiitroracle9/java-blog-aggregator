package org.sivaiitroracle9.spring.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.sivaiitroracle9.rss.ObjectFactory;
import org.sivaiitroracle9.rss.TRss;
import org.sivaiitroracle9.rss.TRssChannel;
import org.sivaiitroracle9.rss.TRssItem;
import org.sivaiitroracle9.spring.entity.Item;
import org.sivaiitroracle9.spring.exception.RssException;
import org.springframework.stereotype.Service;

@Service
public class RssService {

	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}

	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}

	private List<Item> getItems(Source source) throws RssException {
		List<Item> items = new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source,
					TRss.class);
			TRss rss = jaxbElement.getValue();

			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel channel : channels) {
				List<TRssItem> rssItems = channel.getItem();
				for (TRssItem rssItem : rssItems) {
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					Date pubDate = (new SimpleDateFormat(
							"EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH))
							.parse(rssItem.getPubDate());
					item.setPublishedDate(pubDate);
					items.add(item);
				}
			}

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
		return items;
	}
}
