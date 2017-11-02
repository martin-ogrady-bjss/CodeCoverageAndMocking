
import org.mockito.*;
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


	@Test
	public void testServiceCheck() throws Exception
	{
		StringCheckService stringCheckServiceMock = Mockito.mock(StringCheckService.class);
		Mockito.when(stringCheckServiceMock.isGoodString("abc")).thenReturn(true);
		Mockito.when(stringCheckServiceMock.isGoodString("")).thenReturn(true);
		// Mockito.when(stringCheckServiceMock.isGoodString("abc def")).thenReturn(false);
		Mockito.when(stringCheckServiceMock.isGoodString("blacklistedString")).thenReturn(false);

		// Mockito.replay(stringCheckServiceMock);

		StringChecker checker = new StringChecker(stringCheckServiceMock);
		assertTrue(checker.isGoodString("svc:abc"));
		assertTrue(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
		
		// Mockito.verify(stringCheckServiceMock);
		// Mockito.reset(stringCheckServiceMock);
	}


	@Test
	public void testServiceCheckWhenNullThrown() throws Exception
	{
		StringCheckService stringCheckServiceMock = Mockito.mock(StringCheckService.class);
		Mockito.when(stringCheckServiceMock.isGoodString("abc")).thenThrow(new Exception("Mocking a service exception"));

		StringChecker checker = new StringChecker(stringCheckServiceMock);
		assertFalse(checker.isGoodString("svc:abc"));
		assertFalse(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
	}
}
