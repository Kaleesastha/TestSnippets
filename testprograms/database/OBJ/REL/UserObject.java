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
	
	private int id=0;

	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		
        this.id = id;
	}

	private long timeValue=0;

	public long getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(long timeValue) {
		this.timeValue = timeValue;
	}

}





