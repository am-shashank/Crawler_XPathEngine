/**
 * 
 */
package test.edu.upenn.cis455;

import static org.junit.Assert.assertEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis455.crawler.HTTPClient;

/**
 * @author cis455
 *
 */
public class TestCrawlerHTTPClient extends TestCase {

	HTTPClient client;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/** Check if autoredirection in https is turned off and check if the redirected link is proper 
	 * Test method for {@link edu.upenn.cis455.crawler.HTTPClient#redirectLink()}.
	 * @throws Exception 
	 */
	@Test
	public void testRedirectLink() throws Exception {
		client = new HTTPClient("https://dbappserv.cis.upenn.edu/crawltest/marie/private", "HEAD");
		client.sendRequest();
		assertEquals(client.getRedirectLink(),"https://dbappserv.cis.upenn.edu/crawltest/marie/private/");
		
	}
	
	@Test
	public void testGetDocumentAfterGetBody() {
		try {
			client = new HTTPClient("https://dbappserv.cis.upenn.edu/crawltest/misc/weather.xml", "HEAD");
		} catch (Exception e) {
			assert(false);
		}
		client.sendRequest();
		try {
			client.getBody();
			// can't get document once you have read the body. reader is no longer available
			client.getDocument();
		} catch (Exception e) {
			assert(true);
		}
		
		
	}

}
