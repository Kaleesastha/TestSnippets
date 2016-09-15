// Decompiled by Jad v1.5.7. Copyright 1997-99 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   SelectTest.java

import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientAPI;
import com.adventnet.nms.util.CustomClassInterface;
import java.io.PrintStream;
import java.util.Properties;

public class SelectTest
    implements CustomClassInterface
{

    public void setProperties(Properties aproperties[])
    {
	System.out.println("Selecting Symbols " + aproperties[0]);
	MapClientAPI mapclientapi = MapClientAPI.getInstance();
	DefaultMapModel defaultmapmodel = mapclientapi.getMapModel("ipnet.netmap");
	java.util.Vector vector = defaultmapmodel.getSymbols();
	defaultmapmodel.setSelectedSymbols(vector);
    }
}
