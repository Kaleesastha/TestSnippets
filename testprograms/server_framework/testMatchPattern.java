/* $Id: testMatchPattern.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import com.adventnet.nms.util.NmsUtil;

public class testMatchPattern
{

	public static void main(String args[])
	{
		String[][] tests = {
			 {"Hello","H*","true"},
			 {"Hello","*o","true"},
			 {"Hello","Hel?o","true"},
			 {"Hello","Hmn","false"},
			 {"Hello","Hello","true"},
			 {"Hello","!Hello","false"},
			 {"Hello","!<Hell>","true"},
			 {"Hello","!world","true"},
			 {"Hello","<Hello>","true"},
			 {"Hell","<Hello>","false"},
			 {"Hell","<Hel>","false"},
			 {"Hello","<Hell>o","true"},
			 {"Hello","<Hell>","false"},
			 {"Hello","<World>","false"},
			 {"santu.india.adventnet.com","san*","true"},
			 {"santu.india.adventnet.com","santu.*","true"},
			 {"santu.india.adventnet.com","santu.india.adventnet.com","true"},
			 {"Hello","Hel","true"}, //Since we are checking startsWith
			 {"Hello","ello","false"},
			 {"123","<between> 100 and 150","true"},
			 {"321","<between> 321 and 150","false"},
			 {"321","<between> 321 and 450","true"},
			 {"321","<between> 322 and 450","false"},
			 {"123","<between> 150 and 250","false"},
			 //{"123","<between> 100 and Hxy","false"}, throws null pointer Ex.
			 {"321","H<betwen>o","false"},
			 {"Hello","H*o","true"}
		};

		for(int i = 0; i < tests.length; i++)
		{
			String str = tests[i][0];
			String pat = tests[i][1];
			String expected = tests[i][2];

			boolean resb = NmsUtil.checkOneString(pat,str);

			String res = String.valueOf(resb);

			if(res.equals(expected))
			{
			   System.out.println("Passed");
			}
			else
			{
				System.out.println("str = " + str + " pat = " + pat);
			   System.out.print("Expected result = " + expected  + " (but " + res + ")::::");
			   System.out.println("Failed");

			}


		}
	}
}
