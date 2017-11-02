
import org.easymock.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ NetworkStringCheckService.class })
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
		PowerMock.mockStatic(NetworkStringCheckService.class);
		NetworkStringCheckService stringCheckServiceMock = PowerMock.createMock(NetworkStringCheckService.class);
		EasyMock.expect(NetworkStringCheckService.getInstance()).andReturn(stringCheckServiceMock);
		EasyMock.expect(stringCheckServiceMock.isGoodString("abc")).andReturn(true);
		EasyMock.expect(stringCheckServiceMock.isGoodString("")).andReturn(true);
		// EasyMock.expect(stringCheckServiceMock.isGoodString("abc def")).andReturn(false);
		EasyMock.expect(stringCheckServiceMock.isGoodString("blacklistedString")).andReturn(false);

		PowerMock.replay(NetworkStringCheckService.class);
		EasyMock.replay(stringCheckServiceMock);

		StringChecker checker = new StringChecker();
		assertTrue(checker.isGoodString("svc:abc"));
		assertTrue(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
		
		PowerMock.replay(NetworkStringCheckService.class);
		EasyMock.verify(stringCheckServiceMock);
		
		PowerMock.replay(NetworkStringCheckService.class);
		EasyMock.reset(stringCheckServiceMock);
	}


	@Test
	public void testServiceCheckWhenNullThrown() throws Exception
	{
		StringCheckService stringCheckServiceMock = EasyMock.createMock(StringCheckService.class);
		EasyMock.expect(stringCheckServiceMock.isGoodString("abc")).andThrow(new Exception("Mocking a service exception"));

		StringChecker checker = new StringChecker();
		assertFalse(checker.isGoodString("svc:abc"));
		assertFalse(checker.isGoodString("svc:"));
		assertFalse(checker.isGoodString("svc:abc def"));
		assertFalse(checker.isGoodString("svc:blacklistedString"));
	}
}
