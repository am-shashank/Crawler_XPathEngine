package test.edu.upenn.cis455;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis455.storage.DBEnvWrapper;
import edu.upenn.cis455.storage.DBWrapper;
import edu.upenn.cis455.storage.User;

public class TestDBWrapper extends TestCase {
	
	
	DBEnvWrapper dbew;
	DBWrapper dbWrapper;

	@Before
	public void setUp() throws Exception {
		dbew = new DBEnvWrapper("test");
		dbWrapper = dbew.getUserDatabase();		
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertGet() {
		User user = new User();
		user.setUsername("testusername");
		user.setName("testName");
		user.setPassword("testpassword");
		try {
			dbWrapper.insert("testusername", user);
			User temp = (User) dbWrapper.get("testusername");
			assertEquals(temp.getUsername(), user.getUsername());
		} catch (Exception e) {
			assert(false);
		}
	}
	
	@Test
	public void testDelete() {
		User user = new User();
		user.setUsername("testusername");
		user.setName("testName");
		user.setPassword("testpassword");
		try {
			dbWrapper.insert("testusername", user);
			dbWrapper.delete("testusername");
			User temp = null;
			temp = (User) dbWrapper.get("testusername");
			assertEquals(temp, null);
		} catch (Exception e) {
			assert(false);
		}
		assert(true);
	}
}
