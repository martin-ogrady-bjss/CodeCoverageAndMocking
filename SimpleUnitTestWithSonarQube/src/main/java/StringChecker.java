
public class StringChecker
{
	public void dummy()
	{
		return;
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
		else
		{
			return true;
		}
	}
}
