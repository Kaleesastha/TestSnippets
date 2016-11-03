/*$Id: RmiAdminUi.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)RmiAdminUi.java
 * Copyright (c) 1996-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.security.adminui;

import com.adventnet.security.ui.AuthMain;

public class RmiAdminUi 
{ 
    public static void main(String arg[]) 
    {
        if ( arg.length < 5 )
        {
            System.out.println
                ("Usage : java com.adventnet.nms.example.security.adminui.RmiAdminUi "+
                 "<userName> <password> <hostName> <rmiPort> <httpPort> ");
            System.exit(1);
        }

        java.util.Properties prop = new java.util.Properties(); 
        prop.put("userName", arg[0]); // userName 
        prop.put("password", arg[1]); // password
        prop.put("HOST", arg[2]); // hostName 
        prop.put("PORT",arg[3]); // rmiPortNumber 
        prop.put("HTTP_PORT",arg[4]); // httpPortNumber 

        //If custom authentication has been used give the CRYPTO_CLASS parameter
        //as an argument so that the same can be used in SecurityModelRMIImpl
        //for custom authentication

        if (arg.length == 6)
        {
            if (arg[5] != null)
            {
                prop.put("CRYPTO_CLASS", arg[5]);//No Internationalisation
            }
        }
        prop.put("standalone","true");

        /* 
         *  The Constructor for the security UI accepts the implementations 
         *  for the AbstractSecurityModel which in this case is 
         *      "com.adventnet.security.ui.SecurityModelRMIImpl" and 
         *      "com.adventnet.nms.runtimeconfig.commonBuilderUIStandAloneImpl"
         *  which is the implementations for the AbstractSecurityModel and
	 *  CommonBuilderUIInterface.
         */ 
        
        AuthMain securityclient = new AuthMain
            ("com.adventnet.nms.security.ui.NmsSecurityModelRMIImpl",
             "com.adventnet.nms.runtimeconfig.CommonBuilderUIStandAloneImpl",
             prop);

        securityclient.setVisible(true); 
    }

}
