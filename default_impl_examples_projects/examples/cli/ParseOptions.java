/*$Id: ParseOptions.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)ParseOptions.java
 * Copyright (c) 1996-2002 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation without fee is hereby granted provided
 * that this copyright notice appears in all copies.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL 
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

import java.lang.*;
import java.util.*;
import java.net.*;

public class ParseOptions {
/** The remaining Args after parsing options */
  public String remArgs[]; 

  public String usage_string = null;

  String none = "None";  // to allow for no string attached
  String option = null; // To help with option processing
  int i =0, j=0;        // To help with option processing

/** 
 *  Parses options and sets values according to options. 
 *  options[] is an array of options and values[] is the
 *  returned array of string values.  If options[i] has no
 *  associated string, set values[i] = "None" before calling this.
 *  The usage string must be supplied by the user.
 *  Any remaining arguments are stored in remArgs[]
 */  
  public ParseOptions(String args[], String options[], 
			String values[], String usage) throws Exception {

    Vector remaining = new Vector();  // to store remaining arguments

    usage_string = usage;

    for (i = 0; i < args.length; i++) { // for each arg

      for (j = 0;j<options.length;j++) { // for each option

        if (checkOption(args, options[j], values[j])) { // if we found option
		values[j] = option;
		break;
	} // end if we found option

      }  // end for each option

      if (j<options.length) continue; // if we've found an option, skip

      if (args[i].charAt(0) == '-') usage_error();
      else remaining.addElement(args[i]);
      
    }// end for each arg
    
    remArgs = new String[remaining.size()];  // fill in remArgs[]
    for (i=0;i<remaining.size();i++) 
	remArgs[i] = (String) remaining.elementAt(i);

  }

/** check the specific option */
  private boolean checkOption(String args[], String opt, String value) throws Exception {
     if (args[i].equals(opt)) {
	if (!none.equals(value)) { // has a string att.
	  if (++i < args.length)  option = args[i];
          else usage_error();
	} else option = "Set";  // no string att.
         return true;
     }
     return false;
  }

/** Print usage error and exit */
  public void usage_error() throws Exception{
    System.out.println("Usage: " + usage_string); 
    throw new Exception("usage error");
  }

}
