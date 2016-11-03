/* $Id: UserObject.java,v 1.2 2007/09/27 11:39:59 parimala Exp $ */
package test;

import java.io.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.netwatch.*;
import java.sql.*;
import java.util.Properties;


public class UserObject implements Serializable 
{
    public UserObject()
    {
    }

    
    private String source = "source";    

    /* The method to set source for TestObject  */
    public void setSource(String source)
    {
        this.source = source;
    }

    /* The method to get the source for TestObject*/
    public String getSource()
    {
        return source;
    }
	
	private long id=0;

	public long getId() {
		return id;
	}
    
	private void setId(long id) {
		
        this.id = id;
	}

	private long timeValue=0;

	public long getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(long timeValue) {
		this.timeValue = timeValue;
	}

	private Date dayOfEvent=Date.valueOf("10-8-2001");

	public Date getDayOfEvent() {
		return dayOfEvent;
	}

	public void setDayOfEvent(Date tDate) {
		dayOfEvent=tDate;
	}

}





