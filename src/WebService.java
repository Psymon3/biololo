
public class WebService 
{
	
	private WebService()
	{
	
	}
	
	private static class WebServiceHolder
	{
		private final static WebService instance = new WebService();
	}
	
	public static WebService getInstance()
	{
		return WebServiceHolder.instance;
	}
	
	

}
