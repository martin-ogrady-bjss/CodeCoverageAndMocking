
import org.junit.Test;
import static org.junit.Assert.*;

public class StringCheckerTest
{
	@Test
	public void testGoodString()
	{
		StringChecker checker = new StringChecker();
		assertTrue(checker.isGoodString("abc"));
	}


	@Test
	public void testStringWithSpace()
	{
		StringChecker checker = new StringChecker();
		assertFalse(checker.isGoodString("abc def"));
	}

	
	@Test
	public void testNullString()
	{
		StringChecker checker = new StringChecker();
		assertFalse(checker.isGoodString(null));
	}

/*
	@Test
	public void testServiceCheck()
	{
		StringChecker checker = new StringChecker();
		assertTrue(checker.isGoodString("svc:abc"));
		assertTrue(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
	}
*/

	@Test
	public void testServiceCheck()
	{
		StringCheckService mockStringCheckService = new StringCheckService()
		{
			public boolean isGoodString(String s)
			{
				if(s.equals("blacklistedString")) return false;
				else return true;
			}
		};

		StringChecker checker = new StringChecker(mockStringCheckService);
		assertTrue(checker.isGoodString("svc:abc"));
		assertTrue(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
	}


	@Test
	public void testServiceCheckWhenNullThrown()
	{
		StringCheckService mockStringCheckService = new StringCheckService()
		{
			public boolean isGoodString(String s) throws Exception
			{
				throw new Exception("Mocking a service exception");
			}
		};

		StringChecker checker = new StringChecker(mockStringCheckService);
		assertFalse(checker.isGoodString("svc:abc"));
		assertFalse(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
	}
}
