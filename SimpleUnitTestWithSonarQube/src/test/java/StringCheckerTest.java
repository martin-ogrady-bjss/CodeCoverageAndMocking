
import org.easymock.*;
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
		StringCheckService stringCheckServiceMock = EasyMock.createNiceMock(StringCheckService.class);
		EasyMock.expect(stringCheckServiceMock.isGoodString("abc")).andReturn(true);
		EasyMock.expect(stringCheckServiceMock.isGoodString("")).andReturn(true);
		// EasyMock.expect(stringCheckServiceMock.isGoodString("abc def")).andReturn(false);
		EasyMock.expect(stringCheckServiceMock.isGoodString("blacklistedString")).andReturn(false);

		EasyMock.replay(stringCheckServiceMock);
		
		StringChecker checker = new StringChecker(stringCheckServiceMock);
		assertTrue(checker.isGoodString("svc:abc"));
		assertTrue(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
		
		EasyMock.verify(stringCheckServiceMock);
		EasyMock.reset(stringCheckServiceMock);
	}

/*
	@Test
	public void testServiceCheckWhenNullThrown() throws Exception
	{
		StringCheckService stringCheckServiceMock = EasyMock.createNiceMock(StringCheckService.class);
		EasyMock.expect(stringCheckServiceMock.isGoodString("abc")).andThrow(new Exception("Mocking a service exception"));

		StringChecker checker = new StringChecker(stringCheckServiceMock);
		assertFalse(checker.isGoodString("svc:abc"));
		assertFalse(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
	}
*/
}
