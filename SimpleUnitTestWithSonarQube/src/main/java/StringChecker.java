
public class StringChecker
{
	StringCheckService stringCheckService;
	
	public StringChecker()
	{
		this(new NetworkStringCheckService());
	}
	
	public StringChecker(StringCheckService stringCheckService)
	{
		this.stringCheckService = stringCheckService;
	}
	
	public boolean isGoodString(String s)
	{
		if(s == null)
		{
			return false;
		}
		else if(s.contains(" "))
		{
			return false;
		}
		else if(s.startsWith("svc:"))
		{
			try
			{
				return stringCheckService.isGoodString(s.substring(4));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return false;
			}
		}
		else
		{
			return true;
		}
	}
}
