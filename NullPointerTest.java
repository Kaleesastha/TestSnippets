public class NullPointerTest
{
	public static void main(String[] s){
		String test=null;
		try
		{
			if(1==2)
				test="abc";
			if(test.equalsIgnoreCase("abc"))
			{
				System.err.println("abc matches");
			}
		}
		catch(Exception e)
		{
			if(e.getMessage() != null)
				System.err.println("e.getMessage() is: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
