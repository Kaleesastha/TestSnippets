
package com.adventnet.nms.alertui;

import javax.swing. *;
import java.lang. *;
import java.awt. *;
import java.awt.image. *;
import java.awt.event. *;
import java.util. *;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text. *;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import com.adventnet.nms.util.*;
import com.adventnet.nms.eventui.DEvent;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.startclient.NmsPanel;
import com.adventnet.nms.startclient.NmsCustomPanel;

public class AstroSpecialAlertFilter extends SpecialAlertFilter {

	/*Handle extra date columns (other than deviceTimeStamp / AckDate STARTS here */
	String time5 = ""; //xx
	String time6 = ""; //xx
	String time7 = ""; //xx
	String time8 = ""; //xx
	/*Handle extra date columns (other than deviceTimeStamp / AckDate ENDS here */
	public AstroSpecialAlertFilter (AlertApplet eb){
		super(eb);
	}

	void parseCustomDateColumns(PropForm form, Properties fProps){
		System.err.println("form is : "+form);
		System.err.println("fProps is : "+fProps);
		/*Handle extra date columns (other than deviceTimeStamp / AckDate STARTS here */
		if(fProps != null){
			String timer1 = fProps.getProperty ("deviceTimeStamp");//No Internationalisation
			System.err.println("deviceTimeCriteria in parseDeviceTimeStampTime is : "+timer1);
			String time[] = NmsClientUtil.splitDate (timer1, "MMM dd,yyyy hh:mm:ss a");//No Internationalisation
			time5 = time[0];
			time6 = time[1];

			timer1 = fProps.getProperty ("AckDate");//No Internationalisation
			String time2[] = NmsClientUtil.splitDate (timer1, "MMM dd,yyyy hh:mm:ss a");//No Internationalisation
			time7 = time2[0];
			time8 = time2[1];
			/*Handle extra date columns (other than deviceTimeStamp / AckDate ENDS here */
		}
	}

	public GUIElement[] includeCustomColumns(GUIElement[] props){
		System.err.println("form is : " +form);
		System.err.println("fProp is : " +fProp);
		int len = props.length;
		String toCheck = NmsClientUtil.GetString("To Date/Time (created)");
		GUIElement[] newProps = new GUIElement[len+8];
		int count=0;
		int toExclude = -1;
		System.err.println("time5, time6,time7, time8 : "+time5+", "+time6+", "+time7+", "+time8);
		for(int i=0; i<len; i++){
			if(i == toExclude){
				continue;
			}
			String value = props[i].value;
			if (!toCheck.equalsIgnoreCase(value)){
				newProps[count] = props[i];
				count++;
			}
			else{
				newProps[count] = props[i];
				newProps[count+1] = props[i+1];
				toExclude = i+1;
				/*Handle extra date columns (other than deviceTimeStamp / AckDate STARTS here */
                                newProps[count+2] = new GUIElement ("label", NmsClientUtil.GetString ("From Date/Time (deviceTimeStamp)"));
                                newProps[count+3] = new GUIElement ("time5", "time", time5, false);//No Internationalisation
                                newProps[count+4] = new GUIElement ("label", NmsClientUtil.GetString ("To Date/Time (deviceTimeStamp)"));
                                newProps[count+5] = new GUIElement ("time6", "time", time6, false);
                                newProps[count+6] = new GUIElement ("label", NmsClientUtil.GetString ("From Date/Time (AckDate)"));
                                newProps[count+7] = new GUIElement ("time7", "time", time7, false);
                                newProps[count+8] = new GUIElement ("label", NmsClientUtil.GetString ("To Date/Time (AckDate)"));
                                newProps[count+9] = new GUIElement ("time8", "time", time8, false);
				/*Handle extra date columns (other than deviceTimeStamp / AckDate ENDS here */
				count += 10;
			}
		}
		//parseCustomDateColumns(super.form, super.fProp);
		return newProps;
	}

	public Hashtable validateCustomColumns (PropForm form,Properties columnProps){
		System.err.println("columnProps before validateCustomColumns is : "+columnProps);
		Hashtable propertiesTable = new Hashtable();

		/*Handle extra date columns (other than deviceTimeStamp / AckDate STARTS here */
		String ts = form.getTextFieldVal("time5");//No Internationalisation
		String ts2 = form.getTextFieldVal("time6");//No Internationalisation
		String deviceTimeCriteria = this.dateValidation(ts,ts2,NmsClientUtil.GetString("deviceTimeStamp"));
		String toProceed = "true";
		if(deviceTimeCriteria.equals("false")){
			toProceed = "false";
		}
		else if(!deviceTimeCriteria.equals("true")){
			System.err.println("deviceTimeCriteria is : "+deviceTimeCriteria );
			columnProps.put ("deviceTimeStamp", deviceTimeCriteria);
		}

		ts = form.getTextFieldVal("time7");
		ts2 = form.getTextFieldVal("time8");
		String AckDateCriteria = this.dateValidation(ts,ts2,NmsClientUtil.GetString("Acknowledged"));
		if(AckDateCriteria.equals("false")){
			toProceed = "false";
		}
		else if(!AckDateCriteria.equals("true")){
			columnProps.put("AckDate", AckDateCriteria);
		}
		/*Handle extra date columns (other than deviceTimeStamp / AckDate ENDS here */
		propertiesTable.put("toProceed",toProceed);
		propertiesTable.put("columnProperties", columnProps);
		System.err.println("columnProps after validateCustomColumns is : "+columnProps);
		return propertiesTable;
	}
	public Properties getUserPropForId(String nid)
	{
		Properties tempProp =super.getUserPropForId(nid);
		if( tempProp == null ) return null ;
		//This is just a work around for getting user property at the initial state	
		if( tempProp.size() ==0 ){
			return tempProp;
		}
		/*Handle extra date columns (other than deviceTimeStamp / AckDate STARTS here */
		tempProp.remove("deviceTimeStamp");//No Internationalisation
		tempProp.remove("AckDate");//No Internationalisation
		/*Handle extra date columns (other than deviceTimeStamp / AckDate ENDS here */
		return tempProp;
	}

	public String  dateValidation(String str_Date1, String str_Date2)
	{
		System.err.println("new dateValidation");
		Date dt1=null,dt2=null;
		if(str_Date1 != null && !str_Date1.trim().equals(""))
		{						
			try
			{
				dt1=NmsClientUtil.parseDate(str_Date1,"MMM dd,yyyy hh:mm:ss a");

			}catch (Exception e)
			{
				dt1=null;
				NmsClientUtil.showError (NmsClientUtil.GetString ("Invalid date value") + " " + str_Date1 + " - " + NmsClientUtil.GetString ("Use the pattern") + NmsClientUtil.GetString (NmsClientUtil.NmsFormatter.format (new Date ()))+NmsClientUtil.GetString (" for From time "));
				return "false";
			}
		}			
		if(str_Date2 != null && !str_Date2.trim().equals(""))
		{						
			try
			{
				dt2=NmsClientUtil.parseDate(str_Date2,"MMM dd,yyyy hh:mm:ss a");

			}catch (Exception e)
			{
				dt2=null;
				NmsClientUtil.showError (NmsClientUtil.GetString ("Invalid date value") + " " + str_Date2 + " - " + NmsClientUtil.GetString ("Use the pattern") + NmsClientUtil.GetString (NmsClientUtil.NmsFormatter.format (new Date ()))+" "+NmsClientUtil.GetString ("for To time "));
				return "false" ;
			}
		}			

		System.err.println("dt1, dt2 : "+dt1+", "+dt2);
		if(dt1 == null && dt2 == null)
		{
			return "true";
		}
		else if(dt1 == null || dt2 == null)
		{
			NmsClientUtil.showError(NmsClientUtil.GetString ("Both Dates should be configured"));
			return "false";
		}
		else if(dt1!=null && dt2!=null && dt1.compareTo(dt2)==0)
		{	
			NmsClientUtil.showError(NmsClientUtil.GetString ("Both dates are Equal."));
			return "false";
		}
		else if(dt1!=null && dt2!=null && dt1.compareTo(dt2)>0)
		{
			NmsClientUtil.showError(NmsClientUtil.GetString ("From date value is greater than To Date value."));
			return "false";
		}
		else
		{
			String time1 = "";
			if(dt1 != null)
			{
				time1 = String.valueOf (DEvent.getParsedTime (dt1));
			}
			String time2 = "";
			if(dt2 != null)
			{
				time2 = String.valueOf (DEvent.getParsedTime (dt2));
			}
			return "<between> " + time1 + " and " + time2;
		}

	}

}
