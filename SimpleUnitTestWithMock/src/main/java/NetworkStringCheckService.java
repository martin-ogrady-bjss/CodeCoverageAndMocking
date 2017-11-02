
public class NetworkStringCheckService implements StringCheckService
{
	public boolean isGoodString(String s) throws Exception
	{
		// Code that would ask a network service if a string is good, but instead...
		throw new Exception("Couldn't access service");
	}
}
