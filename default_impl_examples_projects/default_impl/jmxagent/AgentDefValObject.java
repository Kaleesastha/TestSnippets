//$Id: AgentDefValObject.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
package com.adventnet.nms.jmxagent;

import java.io.*;

public class AgentDefValObject implements Serializable
{
    String  name;
    String  value;

    public void setName(String str)
    {
        this.name=str;
    }

    public String getName()
    {
        return this.name;
    }

    public void setValue(String str)
    {
        this.value=str;
    }

    public String getValue()
    {
        return this.value;
    }
}




















