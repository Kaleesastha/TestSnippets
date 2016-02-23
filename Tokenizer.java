package test;
import java.util.*;
import com.adventnet.nms.util.*;
public class Tokenizer
{
	public static void main(String args[])
	{
		Properties p = getFailureInformsProperties(args[0]);
		System.err.println("Properties is : "+p);
	}
	public static Properties getFailureInformsProperties(String debugOutput)
	{
		System.err.println("message is : "+debugOutput);
		Properties failedInformsProperties = new Properties();
		try
		{
			String hostName=null;
			String remotePort=null;
			String userName=null;
			String errorMessage=null;
			String toTokenize = debugOutput;
			StringTokenizer stringTokenizer = new StringTokenizer(toTokenize,":");// No I18N
			while(stringTokenizer.hasMoreElements())
			{
				String token = stringTokenizer.nextToken().trim();
				System.err.println("token is : "+token);
				/*if(token.trim().equals("RemoteHost"))// No I18N
				{
					hostName = stringTokenizer.nextToken();
				}
				else if(token.trim().equals("RemotePort")) // No I18N                                   
				{
					remotePort = stringTokenizer.nextToken();
				}*/
				//else if(token.trim().startsWith("UserName")) // No I18N
				//{
					String temp = token;
					StringTokenizer st = new StringTokenizer(temp,"="); // No I18N
					while(st.hasMoreTokens())
					{
						String subToken =st.nextToken();
						if(subToken.trim().equals("UserName")) // No I18N
						{
							userName = st.nextToken().trim();
						}
						else if(subToken.trim().equals("RemotePort")) // No I18N
						{
							remotePort = st.nextToken().trim();
						}
						else if(subToken.trim().equals("RemoteHost")) // No I18N
						{
							hostName = st.nextToken().trim();
						}
						else
						{
							errorMessage = token.trim();
						}
					}
				//}
			}

			failedInformsProperties.put("hostName",hostName);// No I18N
			failedInformsProperties.put("remotePort",remotePort);// No I18N
			failedInformsProperties.put("userName",userName);// No I18N
			failedInformsProperties.put("errorMessage",errorMessage);// No I18N

		}
		catch(Exception e)
		{
			e.printStackTrace();
			NmsLogMgr.EVENTERR.fail(NmsUtil.GetString(" Exception  while processing the v3 inform authentication failure message "+e),null); // No I18N
		}
		return failedInformsProperties;
	}
}

