package test.edu.upenn.cis455;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RunAllTests extends TestCase 
{
  public static Test suite() 
  {
    try {
      Class[]  testClasses = {
        /* TODO: Add the names of your unit test classes here */
         Class.forName("test.edu.upenn.cis455.TestXPathEngineImpl"),
         Class.forName("test.edu.upenn.cis455.TestDBWrapper"),
         Class.forName("test.edu.upenn.cis455.TestRobotsTxtInfo"),
         Class.forName("test.edu.upenn.cis455.TestXPathCrawler"),
         Class.forName("test.edu.upenn.cis455.TestCrawlerHTTPClient")
      };   
      
      return new TestSuite(testClasses);
    } catch(Exception e){
      e.printStackTrace();
    } 
    
    return null;
  }
}
