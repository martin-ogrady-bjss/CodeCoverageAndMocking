
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

/*	
	@Test
	public void testNullString()
	{
		StringChecker checker = new StringChecker();
		assertFalse(checker.isGoodString(null));
	}
*/
}
