/* $Id: JspUtility.java,v 1.2 2007/02/23 10:50:06 srajeswari Exp $ */

package com.adventnet.nms.jsp;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Properties;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.map.common.MapIconDataReader;

import com.sun.org.apache.xerces.internal.dom.TreeWalkerImpl; 
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class JspUtility {    

	static Properties charsetTable = null;

    private static String language = "en";
	/**
	Parses the javax.servlet.http.HttpServletRequest object and returns all the parameters and thier values in a java.util.Properties object. The request parameter names are set as keys and parameter values are set s thier values.
	@param request the javax.servlet.http.HttpServletRequest object which needs to parsed
	@return Returns the java.util.Properties object which contains the request parameter names and values.
	*/
	public static Properties parseRequestData(HttpServletRequest request) {
		Properties  parameters = new Properties();
		for(Enumeration parameterNames = request.getParameterNames();parameterNames.hasMoreElements();) {
			String paramName = (String)parameterNames.nextElement();
			String value = request.getParameter(paramName);
			if (value == null) value = "-";
                        try
                        {
                            if (language.equals("zh"))
                                value = new String(value.getBytes("8859_1"), "gb2312");
                            else if (language.equals("ja"))
                                value = new String(value.getBytes("8859_1"), "SJIS");
                        }
                        catch(Exception e){}
			parameters.setProperty(paramName,value);
		}
		return parameters;
	}

	/**
	Used to add the elements of Vector in another Vector and return the resultant vector.
	@param preferece java.util.Vector object which contains the elements
	@param data java.util.Vector object which contains the elements to be appended the first Vetcor object
	*/
	public static Vector getOrderData(Vector preference,Vector data) {

		if(data!=null) {
			for(Enumeration en = data.elements();en.hasMoreElements();) {
				String tempObj = (String)en.nextElement();
				tempObj=tempObj.trim();
				if(!preference.contains(tempObj)) {
					preference.addElement(tempObj);
				}
			}
		}
		return preference;            
	}
	/**
	 * Returns the preference order as a java.util.Vector object
	 *
	 * @param showorder the java.lang.String object 
	 *
	 **/
	public static Vector getPreferenceOrder(String showorder){
		Vector preferenceOrder = null;
		if(showorder!= null) {
			preferenceOrder = new Vector();
			StringTokenizer tok = new StringTokenizer(showorder,",\t\n\r\f ",false);
			while(tok.hasMoreTokens()) {
				String tmp=tok.nextToken().trim();
				preferenceOrder.addElement(tmp);
			}
		}
		return preferenceOrder;
	}
	
	public static Properties mergeProperties(Properties one, Properties two) {
		String key;
		String value;
		Enumeration en;
		if(one.size() > two.size()) {
			for(en = two.keys();en.hasMoreElements();) {
				key   = (String)en.nextElement();
				if(!one.containsKey(key)){
					value = two.getProperty(key);
					one.setProperty(key,value);
				}
			}
			return one;
		} else {
			for(en = one.keys();en.hasMoreElements();) {
				key   = (String)en.nextElement();
				//if(!two.containsKey(key))
				if(!two.containsKey(key)){
					value = one.getProperty(key);
					two.setProperty(key,value);
				}
			}
			return two;
		}
	}
	public static Properties getPropertiesFromString(String propString) {
		Properties properties = new Properties();
		String key;  
		StringTokenizer stoken = new StringTokenizer (propString,"=;",true);
		while(stoken.hasMoreTokens()) {
			key = stoken.nextToken();
			stoken.nextToken();
			String value = stoken.nextToken();
			if(value.equals(";")) {
				value = "";
			} else
				stoken.nextToken();
			properties.setProperty(key,value);
		}
		return properties;        
	}
	public static String getStringFromProperties(Properties properties) {
		properties.remove("userName");
		properties.remove("keyForCvc");
		StringBuffer toReturn = new StringBuffer();
		for(Enumeration en = properties.keys();en.hasMoreElements();) {
			String key   = (String)en.nextElement();
			String value = properties.getProperty(key);
			//toReturn     = toReturn + key + "=" + value + ";";
			toReturn.append(key);
			toReturn.append("=");
			toReturn.append(value);
			toReturn.append(";"); 
		}
		return toReturn.toString();
	}

	/**
	 * Returns the attributes and values as java.util.Properties object
	 *
	 * @param buffer the java.lang.String object which contains the attributes and values 
	 *
	 **/
	public Properties  getProperty(String buffer)
	{
		Properties prop=new Properties();
		StringTokenizer token=new StringTokenizer(buffer,";");
		while(token.hasMoreTokens()) {
			String tmp=token.nextToken();
			StringTokenizer key=new StringTokenizer(tmp,"=");
			Vector v=new Vector();
			while(key.hasMoreTokens()) {
				String tmp1=key.nextToken();
				v.addElement(tmp1);
			}
			prop.setProperty(v.elementAt(1).toString().trim(),v.elementAt(0).toString().trim()); 
		}
		return prop	;
	}

	public static String getValue(String stringSeverity) {
		String token="";
		String intSeverityString="";
		StringTokenizer commaTokenizer=null;
		SeverityInfo  severityInfo = SeverityInfo.getInstance(); 		
		if(stringSeverity.indexOf(",") != -1){
			commaTokenizer=new StringTokenizer(stringSeverity,",");
			int tokenCount=commaTokenizer.countTokens();
			for(int i=0;i<tokenCount;i++){
				token=commaTokenizer.nextToken();
				intSeverityString+=severityInfo.getValue(token);
				if(i==(tokenCount-1)){
				} else {
					intSeverityString+=",";
				}
			}
		} else {
			intSeverityString=""+severityInfo.getValue(stringSeverity);
		}
		severityInfo=null;
		commaTokenizer=null;
		return intSeverityString;
	}
	public static String getName(String intSeverity) {
		String severityString="";
		String token="";
		StringTokenizer commaTokenizer=null;
		SeverityInfo  severityInfo = SeverityInfo.getInstance(); 		
		if(intSeverity.indexOf(",") != -1){
			commaTokenizer=new StringTokenizer(intSeverity,",");
			int tokenCount=commaTokenizer.countTokens();
			for(int i=0;i<tokenCount;i++){
				token=commaTokenizer.nextToken();
				int intToken=Integer.parseInt(token);
				if(severityInfo.contains(intToken)){
					severityString+=severityInfo.getName(intToken);
				}else{
					severityString+="";
				}
				if(i==(tokenCount-1)){
				} else {
					severityString+=",";
				}
			}
		} else {
			int severity = Integer.parseInt(intSeverity);
			if(severityInfo.contains(severity)){
				severityString = severityInfo.getName(severity);
			} else {
				severityString+="-";
			}
		}
		severityInfo=null;
		commaTokenizer=null;
		return severityString;
	}

	/**
	Used to get the keys of a Properties object in a Vector.
	@param prop the Properties object for which you need the keys in a Vector
	@return Returns the vector which contains the keys of the supplied Properties object
	*/
	public static Vector getKeys(Properties prop) {
		Vector parameters = new Vector();
		for(Enumeration parameterNames = prop.keys();parameterNames.hasMoreElements();) {
			String param = (String)parameterNames.nextElement();
			param=param.trim();
			parameters.addElement(param);
		}
		return parameters;
	}

	/**
	Used to get the date and time in a human readable format.
	@return the date and time in a readble format
	*/
	public static String gettime() {

        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int dmonth =cal.get(cal.MONTH) + 1;
        String datestr = " " + dmonth+"/"+cal.get(cal.DAY_OF_MONTH)+"/"+cal.get(cal.YEAR)+" Time "+cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+"  ";
        return datestr;
    }

	/**
	This method can be used to reduce the length of the given string if its' length exceeds 25 characters. 
	It appends dots after 22 characters and the returned string can be used for displaying in the UI.
	@param label the string that needs to be trimmed.
	*/
	public static String editLabel(String label) {

		if( (label == null) || ( label != null && label.equals("")) ){
			return "-";
		}
		else {
			if(label.length()>25) {
				label=label.substring(0,22)+"...";
			}
			return label;
		}
	}

	/**
	Used to get the string objType when integer is given. 
	@param intObjType the integer objType for which you want the corresponding string value
	@return the objType represented as a string value.
	*/
	public static String getStrObjType(String intObjType) {

		MapIconDataReader mapIconReader = new MapIconDataReader(com.adventnet.nms.util.PureUtils.rootDir+"/conf/mapIcon.data");
		Properties objTypes = mapIconReader.getObjTypes();
		Enumeration e = objTypes.keys();
		String key = null;
		String value = null;
		boolean match = false;
		while(e.hasMoreElements()){
			key = (String)e.nextElement();
			value = objTypes.getProperty(key);
			if(value.equals(intObjType)){
				match = true;
				break;
			}
		}
		if(!match){
			key = intObjType;	
		}
		return key;
	}

	public static String[] parseTime(String time) {    

		String time1 = "";
		String time2 = "";
        if (time == null ) {
			time1="";
			time2="";
			return null;
		}
        if (!time.startsWith("<between>")) {
			time1="";
			time2="";
		} 
        int indexOfand = time.indexOf("and");
        if (indexOfand < 0) {
            indexOfand = time.length();
            time2 = "";
        } else {
            try {
                String extension = time.substring(indexOfand+3).trim();
                time2 = dateString(extension);
            } catch (Exception e) {
				time2 = "";
			}
        }
        int indexOfgt = time.indexOf(">");
        try {
            String starttime = time.substring((indexOfgt+1),indexOfand).trim();
            time1 = dateString(starttime);
        } catch (Exception e) {
			time1 = "";
		}
		String timeArray[] = { time1, time2 };
		return timeArray;
    }

	public static String dateString(String timeInLong) {

		String dateString = "";
		try {
			if ((timeInLong==null) || (timeInLong.equals(""))) {
				dateString = "";
			} else {
				long longtime = Long.parseLong(timeInLong);
				dateString = (new Date(longtime)).toString();
			}
		} catch (Exception e) {
			dateString = "";
		}
		return dateString;
	}

	/**
	* Used to get the charset (which can be used to set the content type)
	* based on the given langugage and country.
	* @param lang the language in the standard two charatcer format.
	* @param country the country in the standard two character format.
	* @return the charset for the given language and country.
	*/
	public static String getContentType(String lang, String country) {

		if ( lang == null ){
			return "ISO-8859-1";
		}
		lang=lang.trim();
                language = lang;

		if( charsetTable == null ){
			charsetTable=loadProperties();
		}

		if (lang.equals("zh")) {
			if(country.equals("CN"))
				return "GB2312";
			else 
				return "Big5";
		}

		for(Enumeration e= charsetTable.propertyNames();e.hasMoreElements();) {
			String key = (String)e.nextElement();
			if(lang.equals(key)) {
				return charsetTable.getProperty(key);
			}
		}
		return "ISO-8859-1";
	}

    private static Properties loadProperties()
    {
		Properties table = new Properties();
		table.put("sq","ISO-8859-2");
		table.put("ar","ISO-8859-6");
		table.put("bg","ISO-8859-5");
		table.put("be","ISO-8859-5");
		table.put("ca","ISO-8859-1");
		table.put("hr","ISO-8859-2");
		table.put("cs","ISO-8859-2");
		table.put("da","ISO-8859-1");
		table.put("nl","ISO-8859-1");
		table.put("en","ISO-8859-1");
		table.put("et","ISO-8859-1");
		table.put("fi","ISO-8859-1");
		table.put("fr","ISO-8859-1");
		table.put("de","ISO-8859-1");
		table.put("el","ISO-8859-7");
		table.put("he","ISO-8859-8");
		table.put("hu","ISO-8859-2");
		table.put("is","ISO-8859-1");
		table.put("it","ISO-8859-1");
		//table.put("ja","Shift_JIS,ISO-2022-JP,EUC-JP");
                table.put("ja","Shift_JIS");
		table.put("ko","EUC-KR");
		table.put("lv","ISO-8859-2");
		table.put("lt","ISO-8859-2");
		table.put("mk","ISO-8859-5");
		table.put("no","ISO-8859-1");
		table.put("pl","ISO-8859-2");
		table.put("pt","ISO-8859-1");
		table.put("ro","ISO-8859-2");
		//table.put("ru","ISO-8859-5,KOI8-R");
                table.put("ru","ISO-8859-5");
		table.put("sr","ISO-8859-KOI8-R");
		//table.put("sh","ISO-8859-5,ISO-8859-2,KOI8-R");
                table.put("sh","ISO-8859-5");
		table.put("sk","ISO-8859-2");
		table.put("sl","ISO-8859-2");
		table.put("es","ISO-8859-1");
		table.put("sv","ISO-8859-1");
		table.put("tr","ISO-8859-9");
		//table.put("uk","ISO-8859-5,KOI8-R");
                table.put("uk","ISO-8859-5");
		return table;
	}
	
	/**
	This method can be used to get the table columns. This method returns the column name and the 
	display name in a properties object. It ignores the width as it is not used in the HTML Client.
	@param tableColumnsStr the table columns as a string object.
	@return Properties the column name and display name are returned.
	*/
	public static Properties getTableColumns(String tableColumnsStr){

		if( tableColumnsStr == null || tableColumnsStr.trim().equals("") 
												|| tableColumnsStr.equals("null") ){
			return null;
		}
		StringTokenizer st = new StringTokenizer(tableColumnsStr,";");
		Properties p = new Properties();
		while(st.hasMoreTokens()){
			StringTokenizer s = new StringTokenizer(st.nextToken(),"=");
			String displayName = s.nextToken();
			String columnName = s.nextToken();
			p.setProperty(columnName, displayName);
		}
		return p;
	}

	/**
	Used to get the column names in a Vector. 
	@param tableColumnsStr the table columns as a string object.
	@return Vector a vector which contains the columns names from the given table columns. 
	*/
	public static Vector getColumnNames(String tableColumnsStr){
		
		if( tableColumnsStr == null || tableColumnsStr.trim().equals("") 
												|| tableColumnsStr.equals("null") ){
			return null;
		}
		StringTokenizer st = new StringTokenizer(tableColumnsStr,";");
		Vector columnNames = new Vector();
		while(st.hasMoreTokens()){
			StringTokenizer s = new StringTokenizer(st.nextToken(),"=");
			s.nextToken(); // ignoring the display name
			columnNames.addElement(s.nextToken());
		}
		return columnNames;
	}

	/**
	* To get the matching Node from the given root element based on the 
	* given attribute name and attribute value.
	* @param root the root element from which you want the matching node.
	* @param attributeName the attributeName based on which the node has to returned.
	* @param attributeValue the attributeValue for which the matching node has to be returned.
	* @return the matching node.
	*/
	public static Node getNode(Element root, String attributeName, String attributeValue )
	{
		if( root == null )
		{
			return null;
		}
		TreeWalkerImpl treeWalker = new TreeWalkerImpl(root,0,null,false);// As this method is no longer used in WebNMS, Dummy implementation made to avoid JDK1.5 Compilation error.ie, Crimson APIs changed into Xerces APIs
		Node node = null;
		boolean nodeMatched = false;
		while( (node = treeWalker.nextNode()) != null )
		{
			NamedNodeMap nodeMap = node.getAttributes();
			int length = nodeMap.getLength();
			for( int i=0; i<length; i++ )
			{
				Node attributeNode = nodeMap.item(i);
				String attributeNodeName = attributeNode.getNodeName();
				if( attributeNodeName.equals(attributeName) )
				{
					String attributeNodeValue = attributeNode.getNodeValue();
					if( attributeNodeValue.equals(attributeValue) )
					{
					    nodeMatched = true;
						break;
					}
				}
			}
			if(nodeMatched)
			{
			    break;
			}
		}
		return node;
	}

	/**
	* To get the attribute value for the given attribute name from the given node.
	* @param node the node from which you need the attribute value.
	* @param attributeName the attribute name for which the attribute value is required.
	* @return  the attribute value 
	*/
	public static String getAttributeValue(Node node, String attributeName)
	{
		if( node == null )
		{
			return null;
		}
		String attributeValue = null;
		NamedNodeMap nodeMap = node.getAttributes();
		int length = nodeMap.getLength();
		for( int i=0; i<length; i++)
		{
			Node attributeNode = nodeMap.item(i);
			String attributeNodeName = attributeNode.getNodeName();
			if( attributeNodeName.equals(attributeName) )
			{
				attributeValue = attributeNode.getNodeValue();
			}
			
		}
		return attributeValue;
	}
}
