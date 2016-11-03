package test;

import com.adventnet.nms.severity.SeverityInfo;
import java.util.*;

/**
 * This is an Utility class which maintains the count against each severity.
 * For each and every unique source, this object will be created.
 * This object is used to represent the entity-severity counts belong
 * to a particular source.
 */
public class EventCountData
{
    private Hashtable dataTable = null;
    private String source=null;

    /**
     * Constructor which accepts the source field of Event as an argument.
     * Initialized the total row
     */
    public EventCountData(String source)
    {
        this.source=source;
        dataTable = new Hashtable();
        dataTable.put("Total",formProperties());
    }
    
    /**
     * Utility method to fill default values for each and every column
     */
    private Properties formProperties()
    { 
        Properties countProperties = new Properties();
        SeverityInfo info = SeverityInfo.getInstance();
        Vector sevNames = info.getNames();
        sevNames.remove("Unknown");
        for(int i=0;i<sevNames.size();i++)
        {
            String name=(String)sevNames.elementAt(i);
            int sevValue = info.getValue(name);
            countProperties.setProperty(String.valueOf(sevValue),"0");
        }
        return countProperties;
    }

    /**
     * Method used to increase the Event Count by one in the specified entity and severity
     */
    public void increaseOne(String entity,int severity)
    {
        Properties countProperties = (Properties)dataTable.get(entity);
        if(countProperties == null)
        {
            countProperties = formProperties();
            dataTable.put(entity,countProperties);
        }
        String value = countProperties.getProperty(String.valueOf(severity));
        if(value == null)
        {
            return;
        }
        else
        {
            int count = Integer.parseInt(value);
            countProperties.setProperty(String.valueOf(severity),String.valueOf(count+1));
        }

        // to increment one in total
        Properties totalProperties = (Properties)dataTable.get("Total");
        String total = totalProperties.getProperty(String.valueOf(severity));
        if(total == null)
        {
            return;
        }
        else
        {
            int count = Integer.parseInt(total);
            totalProperties.setProperty(String.valueOf(severity),String.valueOf(count+1));
        }
    }

    /**
     * Returns source of this Event data
     */
    public String getSource()
    {
        return this.source;
    }

    /**
     * Returns EventCountData in the form of Hashtable
     */
    public Hashtable getData()
    {
        return dataTable;
    }
}
