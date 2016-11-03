/* $Id: ScriptHandlerExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ScriptHandlerExample.java
 * Copyright (c) 2001-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

import com.adventnet.util.script.ScriptHandler;
import com.adventnet.cli.transport.TelnetProtocolOptionsImpl;
import java.util.*;

/**
 * This Sample application makes use of ScriptHandler API's to invoke and
 * execute a script. The CLI API's will be used in the script, to open a
 * Dedicated CLISession and send Synchronous CLI Commands to the Device.
 *
 * Options:
 * <pre>
 * ScriptFile    Mandatory     - The name of file containing the script. 
 * <pre>
 *
 * Example Usage: java ScriptHandlerExample CliSyncApp.bsh
 * @since CLI2.0
 */
public class ScriptHandlerExample
{
    public ScriptHandlerExample ()
    {
        
    }

    public static void main(String args[]){
	String usage = "java ScriptHandlerExample ScriptFile \n Example Usage: java ScriptHandlerExample CliSyncApp.bsh \n";

	if (args.length < 1) {
            System.err.println(" USAGE : "+usage);
            System.exit(1);
        }
	
        // set the script file.
        String scriptFile = args[0];

        String scriptType = null;

        // setting the extension as script type.
        StringTokenizer strTok = new StringTokenizer(scriptFile, ".");
        if (strTok.countTokens()==2){
            strTok.nextToken();
            scriptType = strTok.nextToken();
        }else{
            System.err.println(" File name should have an extension.");
            System.exit(1);
        }
        
        try{
            String[] telnetOptions = new String[args.length-1];
            // removing the ScriptFile from the arguments list to the script.
            for(int i=1;i<args.length;i++){
                telnetOptions[i-1] = args[i];
            }
            // instantiating ScriptHandler.
            ScriptHandler sh = new ScriptHandler();
            // invoking ScriptHandler's method to execute script.
            sh.executeScriptFromFile(scriptFile,telnetOptions,scriptType);
            System.exit(1);
        }catch (Exception e){
            System.err.println(" exception "+e);
            e.printStackTrace();
        }
    }
}// ScrMain
