
import org.junit.Test;
import static org.junit.Assert.*;

public class NetworkStringCheckServiceTest
{
	@Test(expected = Exception.class)
	public void testExceptionThrown() throws Exception
	{
		NetworkStringCheckService svc = NetworkStringCheckService.getInstance();
		svc.isGoodString("abc");
	}
}
