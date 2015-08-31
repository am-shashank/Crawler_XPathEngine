/**
 * 
 */
package test.edu.upenn.cis455;

import static org.junit.Assert.*;

import java.net.URLDecoder;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis455.crawler.HTTPClient;
import edu.upenn.cis455.crawler.info.RobotsTxtInfo;

/** Tests if robots.txt is being parsed appropriately
 * @author Shashank
 *
 */
public class TestRobotsTxtInfo extends TestCase {

	HTTPClient client;
	RobotsTxtInfo robot;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testStarUserAgent() throws Exception {
		client = new HTTPClient("http://en.wikipedia.org/robots.txt","GET");
		client.sendRequest();
		robot = new RobotsTxtInfo(client.getReader());
		assertEquals(robot.getUserAgent(),"*");
		
	}
	
	@Test
	public void testCis455CrawlerUserAgent() throws Exception {
		client = new HTTPClient("https://dbappserv.cis.upenn.edu/robots.txt","GET");
		client.sendRequest();
		robot = new RobotsTxtInfo(client.getReader());
		assertEquals(robot.getUserAgent(),"cis455crawler");
	}
	
	@Test
	public void testNoRobotsTxt() throws Exception {

	}
	
	@Test
	public void checkURLDecoded() throws Exception {
		client = new HTTPClient("http://en.wikipedia.org/robots.txt","GET");
		client.sendRequest();
		robot = new RobotsTxtInfo(client.getReader());
		assertEquals(robot.getAllowedLinks().get(0),URLDecoder.decode(robot.getAllowedLinks().get(0), "UTF-8"));
	}
	@Test
	public void checkNoWildCharacterLinks() throws Exception {
		client = new HTTPClient("https://github.com/robots.txt","GET");
		client.sendRequest();
		robot = new RobotsTxtInfo(client.getReader());
		for(String link: robot.getAllowedLinks())
			assertFalse(link.matches("[$?*]"));
		for(String link: robot.getDisallowedLinks())
			assertFalse(link.matches("[$?*]"));
	}
	
	

}
