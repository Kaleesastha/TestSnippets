//$Id: DoubleManagedObject.java,v 1.3 2005/08/23 13:15:48 sunilg Exp $
//NOTE: This works for oracle only and with FLOAT as data type
//Use  RelationalDoubleManagedObject.txt
package test;
import com.adventnet.nms.topodb.ManagedObject;

public class DoubleManagedObject extends ManagedObject
{

    private double doubleValue=0.00;
    
    public void setDoubleValue(double val)
    {
        this.doubleValue=doubleValue;        
    }

    public double getDoubleValue()
    {
        return doubleValue;
    }
}



