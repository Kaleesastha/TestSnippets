package com.vanu.ems.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import com.adventnet.nms.client.ValidateCredentials;
import com.adventnet.nms.util.NmsClientUtil;
public class PasswordValidator implements ValidateCredentials
{
	
	 /**
	 * @param
	 * @return
	 *	 ^                # start-of-string
	 *	(?=.*[0-9])       # a digit must occur at least once
	 *	(?=.*[a-z])       # a lower case letter must occur at least once
	 *	(?=.*[A-Z])       # an upper case letter must occur at least once
	 *	(?=.*[_@#$%^&+=]) # a special character must occur at least once
	 *	(?=\S+$)          # no whitespace allowed in the entire string
	 *	.{8,16}           # anything, between 8 to 16 places though
	 *	$                 # end-of-string
	 */
    public boolean validate(String userName,String oldPassword, String newPassword, Properties serverProps)
    {
    	String passwdComplexity = null;
		String digits = null;
		String specialChar = null;
		String upperCase = null;
		String lowerCase = null;
		String minCount = null;
		String maxCount = null;
		Properties passwdProp = new Properties();
		try
		{
			//load the URL Properties file
			System.out.println("serverProps for password validator: " + serverProps);
			System.out.println("oldPassword for password validator: " + oldPassword);
			System.out.println("newPassword for password validator: " + newPassword);
			
			if (serverProps != null)
			{
				String host = serverProps.getProperty("host");
				String port = serverProps.getProperty("port");
				String jSessionId = serverProps.getProperty("jsessionid");
				String protocol = serverProps.getProperty("protocol");
				URL url = new java.net.URL(protocol + "://" + host + ":" + port + "/servlets/FetchPasswordValidatorProperties");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				passwdProp.load(in);
				in.close();
				//get the values
				passwdComplexity = passwdProp.getProperty("PASSWORD_COMPLEXITY");
				digits = passwdProp.getProperty("DIGITS");
				specialChar = passwdProp.getProperty("SPECIAL_CHARACTER");
				upperCase = passwdProp.getProperty("UPPERCASE");
				lowerCase = passwdProp.getProperty("LOWERCASE");
				minCount = passwdProp.getProperty("MINIMUM_LENGTH");
				maxCount = passwdProp.getProperty("MAXIMUM_LENGTH");
			}
			else
			{
				return true;
			}
		}
		catch(IOException ex)
		{
			System.out.println("Not able to read properties file for password complexity "+ex.getMessage());
			ex.printStackTrace();
		}
		if(passwdComplexity.equalsIgnoreCase("false"))
		{
			return true;
		}
		String regex = "^";
		String msg = "Password must contain "+ minCount+"-"+maxCount+ " characters which include ";
		if(digits.equalsIgnoreCase("true"))
		{
			regex += "(?=.*[0-9])";
			msg += "a digit, ";
		}
		if(specialChar.equalsIgnoreCase("true"))
		{
			regex += "(?=.*[_@#$%^&+=])";
			msg += "a special character(_ @ # $ % ^ & + =), ";
		}
		if(upperCase.equalsIgnoreCase("true"))
		{
			regex += "(?=.*[A-Z])";
			msg += "an uppercase letter ";
		}
		if(lowerCase.equalsIgnoreCase("true"))
		{
			regex += "(?=.*[a-z])";
			msg += "and a lowercase letter. ";
		}
		regex += "(?=\\S+$).{"+minCount+","+maxCount+"}$";
		msg += "Spaces are not allowed.";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(newPassword);
		if (m.find())
		{
			return true;
		}
		else
        {
            JOptionPane.showMessageDialog(null,msg,"Change Password",JOptionPane.ERROR_MESSAGE);//No I18N
        }
        return false;
    }
    
    public boolean validate(String userName,String oldPassword, String newPassword)
    {
	    return validate(userName,oldPassword,newPassword,null);
    }
}