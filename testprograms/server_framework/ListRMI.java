import java.rmi.*;

public class ListRMI
{

	public static void main(String args[]) throws Exception
	{
		String host = "localhost";
		String portOption = ":1099";
		boolean npflag = false;

		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-h"))
			{
				host = args[i+1];
				i++;
			}
			else if(args[i].equals("-p"))
			{
				portOption = ":" + args[i+1];
				i++;
			}
			else if(args[i].equals("-np"))
			{
				portOption = "";
			}
		}

		String[] list = Naming.list("rmi://" + host +portOption);
                for(int i = 0; i < list.length; i++)
		{
			System.out.println("" + i + ": " + list[i]);
		}

	}
}
