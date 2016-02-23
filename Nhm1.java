import java.io.*

public static void main(String args[])
{
	for(int i=104;i<=105;i++)
	{
		FileInputStream fin=null;
		BufferedReader din=null;
		String line=null;
		String str=null;

		try
		{
			fin = new FileInputStream("C:"+File.separator+"BookFair"+File.separator+"BookFair"+File.separator+"Kizhakku_Books"+File.separator+"other_Stuff"+i+".html");
			din = new BufferedReader(new InputStreamReader(fin));
			while ((line = din.readLine()) != null)
			{
				if (!line.indexOf("--main content--")
						{ 
							continue;
						}
						else
						{
							while(!(line=din.readLine().indexOf("Footer starts here")))
				{
					str=str+line;
				}
						}
			}
			}catch(Exception e)
			{
				putLog(e.getMessage(),str);
			}
	}
}
}
}
