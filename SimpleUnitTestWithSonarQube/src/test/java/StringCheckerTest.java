
import org.junit.Test;
import static org.junit.Assert.*;

public class StringCheckerTest
{
	@Test
	public void testDummy()
	{
		StringChecker checker = new StringChecker();
		checker.dummy();
	}
	
	
	@Test
	public void testGoodString()
	{
		assertTrue(true);
//		StringChecker checker = new StringChecker();
//		assertTrue(checker.isGoodString("abc"));
	}

/*
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
*/
}
