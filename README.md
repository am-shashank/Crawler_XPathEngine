# Crawler + XPathEngine or RSS Aggregator
A topic-specific crawler looks for documents or data matching a particular category. Here, the category will be specified as an XPath expression. This will entail: 
* writing a servlet-based Web application that runs on the application server and allows users to create topic-specific "channels" defined by a set of XPath expressions, and to display documents that match a channel using a referenced XSL stylesheet; 
* writing an XPath evaluation engine that determines if an HTML or XML document matches one of a set of XPath expressions; 
* writing a crawler that traverses the Web, looking for HTML and XML documents that match one of the XPath expressions; and 
* building a persistent data store, using Oracle Berkeley DB, to hold retrieved HTML/XML documents and channel definitions. The resulting application will be very versatile; one use of it could be to write an RSS aggregator with keyword-defined channels. In this case, the XPath expressions would find RSS feeds (which are just XML documents) that contain articles with the specified keywords, and the stylesheet would select the matching articles from the feeds and format them for the user. Later, we will provide an XSL stylesheet and XPath expressions for a sample query over RSS documents. However, the application will have many other uses as well. 
* a Web crawler that will look for relevant XML documents
* a storage capability for HTML and XML documents that the crawler has retrieved. BerkleyDB is used for this persistent storage.

## Crawler
Web crawler will be a Java application that can be run from the command line, as edu.upenn.cis455.crawler.XPathCrawler. It will take the following command-line arguments (in this specific order, and the first three are required):
* The URL of the Web page at which to start. Note that there are several ways to open the URL.
** For plain HTTP URLs you will probably get the best performance by just opening a socket to the port (we’ve provided the URLInfo class to help parse the pieces out).
** For HTTPS URLs you may want to use java.net.URL’s openConnection() method and cast to javax.net.ssl.HttpsURLConnection. This in turn has input and output streams as usual.
* The directory containing the BerkeleyDB database environment that holds your store. The directory should be created if it does not already exist. Your crawler should recursively follow links from the page it starts on. (Note: the store servlet takes the path from web.xml while the crawler expects it as a command-line argument. You may assume that these paths are the same.)
* The maximum size, in megabytes, of a document to be retrieved from a Web server
* An optional argument indicating the number of files (HTML and XML) to retrieve before stopping. This will be useful for testing!
It is intended that the crawler be run periodically, either by hand or from an automated system
like cron command. So there is therefore no need to build a connection from the Web interface to
the crawler.
The crawler traverses links in HTML documents. You can extract these using a HTML parser, such as the Mozilla parser (http://mozillaparser.sourceforge.net/), TagSoup (http://ccil.org/~cowan/XML/tagsoup/), JTidy (http://jtidy.sourceforge.net/) or simply by searching the HTML document for occurrences of the pattern href="URL" and its subtle variations. If a link points to another HTML document, it should be retrieved and scanned for links as well. If it points to an XML or RSS document, it should be retrieved as well. Don't bother crawling images, or trying to extract links from XML files. All retrieved HTML and XML documents should be stored in the database (so that the crawler does not have to retrieve them again if they do not change before the next crawl), but only the XML documents that match one of the XPath expressions should be added to the corresponding channels. The crawler must be careful not to search the same page multiple times during a given crawl, and it should exit when it has no more pages to crawl.
When crawler is processing a new HTML or XML page, it should print a short status report to System.out. Example: "http://xyz.com/index.html: Downloading" (if the page is actually downloaded) or "http://abc.com/def.html: Not modified" (if the page is not downloaded because it has not changed).

### Politeness
Crawler must be a considerate Web citizen. 
* It must respect the robots.txt file, as described in A Standard for Robot Exclusion (http://www.robotstxt.org/robotstxt.html). It must support the Crawl-Delay directive (see http://en.wikipedia.org/wiki/Robots.txt) and "User-agent: *"
* It must always send a HEAD request first to determine the type and size of a file on a Web server. 
