
public class NetworkStringCheckService implements StringCheckService
{
	private static final NetworkStringCheckService instance = new NetworkStringCheckService();
	
	private NetworkStringCheckService()
	{
	}
	
	public static NetworkStringCheckService getInstance()
	{
		return instance;
	}

	public boolean isGoodString(String s) throws Exception
	{
		// Code that would ask a network service if a string is good, but instead...
		throw new Exception("Couldn't access service");
	}
}
