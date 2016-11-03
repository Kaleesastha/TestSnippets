/* $Id: SecurityInvoker.java,v 1.2 2010/10/29 13:45:56 swaminathap Exp $ */
package com.adventnet.security.ui;
import com.adventnet.apiutils.Utility;
import java.util.Properties;

public class SecurityInvoker
{

	public static void main(String args[])
	{
		if(args.length!=2)
		{
			System.out.println("USAGE SecurityInvoke <projectPath> <locale>");//No I18N
			return;
		}
		Properties props= new Properties();
		props.put("projPath",args[0]);//No I18N
		Utility.parseAndSetParameters(new String[]{},new String[]{"-RESOURCE_PROPERTIES","SecurityAdministrationResources","-RESOURCE_LOCALE",args[1]});//Todo get the locale from utility method.//No I18N
		AuthMain securityAdmin= new AuthMain("com.adventnet.security.ui.StandAloneSecurityModel","com.adventnet.studio.security.CommonBuilderStandAloneImpl",props);//No I18N
		securityAdmin.pack();
		securityAdmin.setVisible(true);

	}

}
