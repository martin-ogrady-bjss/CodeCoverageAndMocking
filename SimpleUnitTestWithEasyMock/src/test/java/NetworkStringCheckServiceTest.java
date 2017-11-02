
import org.junit.Test;
import static org.junit.Assert.*;

public class NetworkStringCheckServiceTest
{
	@Test(expected = Exception.class)
	public void testExceptionThrown() throws Exception
	{
		NetworkStringCheckService svc = new NetworkStringCheckService();
		svc.isGoodString("abc");
	}
}
