// $Id: I18NInterface.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 package com.adventnet.management.i18n.tools ; 


 public interface I18NInterface 
 {
	public void find(boolean matchcase);
	public void setSearchText(String searchtext);
	public void setReplaceText(String reptext, String repwithtext);
	public void setAddText(String key, String value);
 }

 



