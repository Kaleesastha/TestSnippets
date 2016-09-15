package com.adventnet.nms.example;

import java.util.Enumeration;
import java.util.Properties;

import com.adventnet.nms.util.*;

public class CustomClass implements com.adventnet.nms.util.CustomClassInterface

{
    public void setProperties(Properties p[])
    {
        
        
        if(p[0].containsKey("nw"))
            NmsClientUtil.showURLInNW (NmsClientUtil.getParentFrame(), "../help/index.html");
        else  if(p[0].containsKey( "sw"))
            NmsClientUtil.showURLInMainPage(NmsClientUtil.getParentFrame(), "../help/index.html");
        
    }
    
} 













