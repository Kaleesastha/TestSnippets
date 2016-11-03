//$Id: HTMLUtility.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package test;

import java.util.*;
import java.awt.Color;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.severity.SeverityInfo;

/**
 * Utility class to create HTML Reports.
 */
public class HTMLUtility
{
    //To store EventCountData versus source
    private Hashtable sourceTable = null;
    //to store the severity names configured in WebNMS
    private String[] severityNames = null;
    //to store the severity values configured in WebNMS
    private int[] severityValues = null;
    //to store the color values of each severity
    private String[] colorStrings = null;
    
    //static instance to maintain single instance per JVM
    private static HTMLUtility util = null;
    
    /**
     * Default constrctor which initialized the utility variables
     */
    public HTMLUtility()
    {
        sourceTable = new Hashtable();
        initializeArrays();
    }

    /**
     * Utility method to initialized the utility variables
     */
    private void initializeArrays()
    {
        SeverityInfo info = SeverityInfo.getInstance();
        Vector vect = info.getNames();
        //to remove unknown severity
        vect.remove("Unknown");
        Object[] array = vect.toArray();
        severityNames = new String[array.length];
        severityValues = new int[array.length];
        colorStrings = new String[array.length];
        for(int i=0;i<array.length;i++)
        {
            String sevName = (String)array[i];
            severityNames[i]=sevName;
            severityValues[i]=info.getValue(sevName);
            colorStrings[i]=getColorString(info.getColor(severityValues[i]));
        }
    }

    /**
     * Utility method to find out html representation of each and every severity color
     * configured in WebNMS.
     */
    private String getColorString(Color color)
    {
        String hexString = "#"+Integer.toHexString(color.getRed())+Integer.toHexString(color.getGreen())+Integer.toHexString(color.getBlue());
        System.out.println(" hexString is =="+hexString);
        return hexString;
    }

    /**
     * Static method to get the instance of this Utility
     */
    public static HTMLUtility getInstance()
    {
        if(util == null)
        {
            util= new HTMLUtility();
        }
        return util;
    }

    /**
     * Method which converts Event objects into the html table
     */
    public String getCountData(Vector objects,String title)
    {
        sourceTable = new Hashtable();
        for(int i=0;i<objects.size();i++)
        {
            Event evt = (Event)objects.elementAt(i);
            String source= evt.getSource();
            String entity= evt.getEntity();
            int severity = evt.getSeverity();
            updateCount(source,entity,severity);
        }

        return htmlString(title);
    }

    private void updateCount(String source,String entity,int severity)
    {
        EventCountData data = (EventCountData)sourceTable.get(source);
        if(data == null)
        {
            data = new EventCountData(source);
            sourceTable.put(source,data);
        }
        data.increaseOne(entity,severity);
    }

    /**
     * Utility method which form HTML table
     */
    private String htmlString(String title)
    {
        StringBuffer buff = new StringBuffer();
        
        String header = "<html> <head> <center> <font size +2> <b>"+title+" </b></font> </center></head> <body> ";
        String table = "<hr WIDTH=\"100%\"><center><table BORDER WIDTH=\"100%\">";
        String firstRow="\n <tr> <th> Source </th> <th> Entity </th>";
        
        buff.append(header);
        buff.append(table);
        buff.append(firstRow);
            
        for(int i=0;i<severityNames.length;i++)
        {
            String name =severityNames[i];
            String color=colorStrings[i];
            if(i == (severityNames.length -1))
            {
                buff.append("<th BGCOLOR=\""+color+"\"> "+name+"</th></tr>");
            }
            else
            {
                buff.append("<th BGCOLOR=\""+color+"\">"+name+ "</th>");
            }
        }

        for(Enumeration e=sourceTable.keys();e.hasMoreElements();)
        {
            String source=(String)e.nextElement();
            buff.append("\n <tr> <td> "+source+" </td>");
            
            EventCountData data = (EventCountData)sourceTable.get(source);            
            Hashtable entityTable = data.getData();
            
            boolean firstTime = true;
            for(Enumeration keys=entityTable.keys();keys.hasMoreElements();)
            {

                String entity =(String)keys.nextElement();
                
                //to get Total as last row for a source
                if(entity.equals("Total"))
                {
                    continue;
                }

                if(firstTime)
                {
                    buff.append("<td> "+entity+" </td>");
                    firstTime=false;
                }
                else
                {
                    buff.append("<tr> <td> </td> <td> "+entity+" </td>");
                }
                fillCountProperties(entityTable,entity,buff);
            }

            //for Totals
            buff.append("<tr> <td> </td> <td> Totals </td>");
            fillCountProperties(entityTable,"Total",buff);
            //for a blank row
            buff.append("<tr><tr><tr>");
        }
        buff.append("\n</table></center></body></html>");
        return buff.toString();
    }

    private void fillCountProperties(Hashtable entityTable,String entity,StringBuffer buff)
    {
        Properties countProperties = (Properties)entityTable.get(entity);
                
        for(int i=0;i<severityValues.length;i++)
        {
            int sevValue = severityValues[i];
            String count=countProperties.getProperty(String.valueOf(sevValue));
            String color=colorStrings[i];
            if(i==(severityValues.length-1))
            {
                buff.append("<td BGCOLOR=\""+color+"\"> "+count+" </td> </tr>");
            }
            else
            {
                buff.append("<td BGCOLOR=\""+color+"\"> "+count+" </td>");
            }
        }
    }
}







