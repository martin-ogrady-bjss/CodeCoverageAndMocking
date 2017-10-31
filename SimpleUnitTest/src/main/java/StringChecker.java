
public class StringChecker
{
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
