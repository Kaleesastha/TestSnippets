// $Id: StringSearch.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.management.i18n.tools ; 


public class StringSearch

{
	boolean found = false;
	int lastindex = 0;	
	
	public StringSearch()
	{
		
	}	
	
	public StringSearch(String pattern)
		{
			String str = "I want to make a quick search algorithm ";
			//System.out.println("Search is :"+ search(str,pattern));
		}

	public static void main(String arg[])
		{
   			 StringSearch ss = new StringSearch(arg[0]);  
		}
	
	public boolean search(String searchstring, String pattern,boolean matchcase)
		{
		
        int index = searchstring.indexOf(pattern.charAt(0));
        if(index == -1)
	   return found;
		
        found = searchstring.regionMatches(matchcase,index,pattern,0,pattern.length());
	   storeParsedChars(searchstring.substring(0,index+1));	
		  if(found)
			{
	       		return found;
			}
	   String sub = searchstring.substring(index+1);
	   search(sub,pattern,matchcase);
        return found;
		   	
		}
	
	StringBuffer sb = new StringBuffer();
		
	public void storeParsedChars(String str)
		{
	 sb.append(str);
		}
	public int getLastindex()
		{
	  return sb.length() -1;
		}	
	public void clearSearch()
	     {
	  sb.delete(0,sb.length()-1);
		}
	

	
}




