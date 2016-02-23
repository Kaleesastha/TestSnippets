
import java.util.*;
/**
 * JavaObjectAndHashCode
 * @author <a href="mailto:karthicks@zohocorp.com"></a>
 */

public class JavaObjectAndHashCode 
{

    public static void main(String[] args) throws Exception
    {
        new JavaObjectAndHashCode();
    }
    static void demoHashCodes()
    {
        //by default each object has unique hashcode
        System.out.println(" (new Object()).hashCode() "+(new Object()).hashCode());
        System.out.println(" (new Object()).hashCode() "+(new Object()).hashCode());
        
        //data based hashcodes. Hashcode method is overridden and its is based on data for some objects. Hashcodes will be same if data is same.
        System.out.println(" (new Integer(10)).hashCode() "+(new Integer(10)).hashCode());
        System.out.println(" (new Integer(10)).hashCode() "+(new Integer(10)).hashCode());
        

        System.out.println(" (new Long(10)).hashCode() "+(new Long(10)).hashCode());

        System.out.println(" (new Double(10)).hashCode() "+(new Double(10)).hashCode());
        System.out.println(" (new Double(10)).hashCode() "+(new Double(10)).hashCode());
        
        System.out.println(" (new String(\"abc\")).hashCode() "+(new String("abc")).hashCode());
        System.out.println(" (new String(\"abc\")).hashCode() "+(new String("abc")).hashCode());

        System.out.println(" (new Long(10)).equals((new Long(10))) "+((new Long(10)).equals((new Long(10)))));
        System.out.println(" HASHCODDE of both Long(10) and new Integer(10) is same but when equate bothe object it will be false");
        System.out.println(" (new Long(10)).equals((new Integer(10))) "+((new Long(10)).equals((new Integer(10)))));
    }

    public JavaObjectAndHashCode() 
    {
        System.out.println(" ---- PointerCheck ---");
        //checks default java object equals method and "==" operator
        new PointerCheck();        
        System.out.println(" ---- Java Data Equals ---");
        //checks java object equals method based on data for specific classes
        new EqualsCheckForData();

        System.out.println(" ---- Custom Object Equals check ---");
        //CustomObjectEqualsCheck
        new CustomObjectEqualsCheck();
        
        System.out.println(" ---- Custom Object Data Equals check ---");
        new CustomObjectDataCheck();

        System.out.println(" ---- Array List of Custom Object when equals method is not overridden ---");
        new ArrayListWithoutDataCheck();

        System.out.println(" ---- Array List of Custom Object when equals method is overridden and data is compared ---");
        new ArrayListWithDataCheck();

        JavaObjectAndHashCode.demoHashCodes();

        System.out.println(" How hash Map works ??? ");
        //It creates bucket say  1-10 11-20 etc. And stores object in buckets based on hashcode. If hashcode is 8 it goest to bucket 1-10. 
        //It is possible two object may have same hashcode but need not equals.
        // In case of same hashcodes and if it not equals , it stores both object in same bucket in same position as two objects. And check equals to uniquely identify the object

        System.out.println(" ---- HashMap with equals overridden and not hashCode ---");
        new HashMapWithoutHashCode();

        System.out.println(" ---- HashMap with equals and hashcode is overridden ---");
        new HashMapWithHashCode();

        System.out.println(" ---- HashMap with same hashcode but different data ---");
        new HashMapAdvanced();
    }

    class PointerCheck
    {
        PointerCheck()
        {
            Object ob1 = new Object(); //new object. Takes own memery space
            Object ob2 = new Object(); //another new object. Takes it own memery space
            
            Object ob3 = ob1; //ob3 point to object ob1. Does not takes separate memory space. Just point to memory space of ob1.
           
            // "==" operator always checks pointer
            System.out.println(" ob1 == ob2 "+(ob1 == ob2)); //false -- because different object
            System.out.println(" ob1 == ob3 "+(ob1 == ob3)); //true -- same objects
           
            //By default java object equals method checks the pointer. It is equal to == operator
            System.out.println(" ob1.equals(ob2) "+(ob1.equals(ob2))); //false            
            System.out.println(" ob1.equals(ob3) "+(ob1.equals(ob3))); //true             
        }    
    }

    class EqualsCheckForData
    {
        EqualsCheckForData()
        {
            Object ob1 = new Integer(10);
            Object ob2 = new Integer(10);
            
            Object ob3 = ob1;
            
            System.out.println(" ob1 == ob2 "+(ob1 == ob2)); //false
            System.out.println(" ob1 == ob3 "+(ob1 == ob3)); //true
            
            //unlike default object 
            // String , Integer,Double,Long, etc checks data. It returns true if data is equal 
            // even if compared Object instance is different
            System.out.println(" ob1.equals(ob2) "+(ob1.equals(ob2))); //true
            System.out.println(" ob1.equals(ob3) "+(ob1.equals(ob3))); //true
        }
    }



    
    //this class does not overrides equals method. And relies on java.lang.Object equals method 
    class CODefaultEquals
    {
        private String data = null;
        CODefaultEquals(String data)
        {
            this.data = data;
        }
    }
    
    class CustomObjectEqualsCheck
    {
                
        CustomObjectEqualsCheck()
        {
            //behaviour is similar as behaviour in PointerCheck      
            Object ob1 = new CODefaultEquals("d1");
            Object ob2 = new CODefaultEquals("d1");
            Object ob3 = ob1;//ob3 point to object ob1. Does not takes separate memory space. Just point to memory space of ob1.
            Object ob4 = new CODefaultEquals("d2");
            
            // "==" operator always checks pointer        
            System.out.println(" ob1 == ob2 "+(ob1 == ob2)); //false
            System.out.println(" ob1 == ob3 "+(ob1 == ob3)); //true
            
            System.out.println(" ob1.equals(ob2) "+(ob1.equals(ob2))); //false data is same but 
            //only pointer is  checked.i.e different instance so it is false 
            System.out.println(" ob1.equals(ob3) "+(ob1.equals(ob3))); //true - same instance so true
        }
        
    }
    

    //overriding equals method and compare data
    class CODataEquals
    {
        private String data = null;
        CODataEquals(String data)
        {   
            this.data = data;
        }
        
        //java.lang.Object equals method is overridden
        public boolean equals(Object obj)
        {
            CODataEquals other = (CODataEquals)obj;
            return other.data.equals(this.data);
        }
    }
    

    class CustomObjectDataCheck
    {
        
        CustomObjectDataCheck()
        {
            Object ob1 = new CODataEquals("d1");
            Object ob2 = new CODataEquals("d1");
            Object ob3 = ob1;
            Object ob4 = new CODataEquals("d2");

            System.out.println(" ob1 == ob2 "+(ob1 == ob2)); //false
            System.out.println(" ob1 == ob3 "+(ob1 == ob3)); //true
            
            System.out.println(" ob1.equals(ob2) "+(ob1.equals(ob2))); //true same data
            System.out.println(" ob1.equals(ob3) "+(ob1.equals(ob3))); //true same instance
            System.out.println(" ob1.equals(ob4) "+(ob1.equals(ob4))); //false different data
        }
        
    }




    class ArrayListWithoutDataCheck
    {
        ArrayListWithoutDataCheck()
        {

            ArrayList list = new ArrayList();
        
            Object ob1 = new CODefaultEquals("d1");
            Object ob2 = new CODefaultEquals("d1");
            Object ob3 = ob1;
            Object ob4 = new CODefaultEquals("d2");

            list.add(ob1);
            list.add(ob4);

            System.out.println(" list.indexOf(ob3)"+list.indexOf(ob3)); // 0 - ob3 is same of ob1. Same instance. Returns position of ob3/ob1.
            System.out.println(" list.indexOf(ob2)"+list.indexOf(ob2)); // -1 - ob2 and ob1 has same data. but different instance. Unable to equalise the object and find the location.  ob2.equals(ob1) is false
            System.out.println(" list.indexOf(ob4)"+list.indexOf(ob4)); // 1 
        
        }
    
    }


    class ArrayListWithDataCheck
    {
        ArrayListWithDataCheck()
        {

            ArrayList list = new ArrayList();
        
            Object ob1 = new CODataEquals("d1");
            Object ob2 = new CODataEquals("d1");
            Object ob3 = ob1;
            Object ob4 = new CODataEquals("d2");

            list.add(ob1);
            list.add(ob4);

            System.out.println(" list.indexOf(ob3)"+list.indexOf(ob3)); // 0 - ob3 is same of ob1. Same instance. Returns position of ob3/ob1.
            System.out.println(" list.indexOf(ob2)"+list.indexOf(ob2)); // 0 - ob2 and ob1 has same data. But different instance. Since equals method is overridden ArrayList is able to equalise the object and find the location. ob2.equals(ob1) is true
            System.out.println(" list.indexOf(ob4)"+list.indexOf(ob4)); // 1 
        
        }
    
    }

    class HashMapWithoutHashCode
    {
        HashMapWithoutHashCode()
        {

            HashMap map = new HashMap();
        
            Object ob1 = new CODataEquals("d1");
            Object ob2 = new CODataEquals("d1");
            Object ob3 = ob1;
            Object ob4 = new CODataEquals("d2");

            map.put(ob1, " its is ob1 ");
            map.put(ob4, " its is ob4 ");

            System.out.println(" map.get(ob3)"+map.get(ob3)); //it is ob1 -gets value- since key is ob1 which is same instance of ob3
            System.out.println(" map.get(ob2)"+map.get(ob2)); //null. key data is "d1" but different instance. So hashcode will be different. It is unable to identify the key or bucket
            System.out.println(" map.get(ob4)"+map.get(ob4)); // it is ob4. key is same instance gets the value 
        
        }
    
    }

    class CODataEqualsHC
    {
        private int hc = -1;
        private String data = null;
        CODataEqualsHC(int hc , String data)
        {   
            this.data = data;
            this.hc = hc;
        }
        
        //java.lang.Object equals method is overridden
        public boolean equals(Object obj)
        {
            CODataEqualsHC other = (CODataEqualsHC)obj;
            return other.data.equals(this.data);
        }

        
        //java.lang.Object hashCode method is overridden. And the passed value is given as has hashCode
        public int hashCode()
        {
            return hc;
        }
    }
    
    class HashMapWithHashCode
    {
        HashMapWithHashCode()
        {

            System.out.println(" some hashcodes ");
            HashMap map = new HashMap();
        
            Object ob1 = new CODataEqualsHC(10,"d1");
            Object ob2 = new CODataEqualsHC(10,"d1");
            Object ob3 = ob1;
            Object ob4 = new CODataEqualsHC(21,"d2");

            map.put(ob1, " its is ob1 ");
            map.put(ob4, " its is ob4 ");

            System.out.println(" map.get(ob3)"+map.get(ob3)); //it is ob1 -gets value- since key is ob1 which is same instance of ob3
            System.out.println(" map.get(ob2)"+map.get(ob2)); //it is ob1. key data is "d1" and hashcode is 10. Able to identify the bucket based on hashcode. Then checks for equals of key. since key data is same ("d1"). It returns true and value is fetched
            System.out.println(" map.get(ob4)"+map.get(ob4)); // it is ob4. key is same instance gets the value 
        }
    
    }

    class HashMapAdvanced
    {
        HashMapAdvanced()
        {

            System.out.println(" some hashcodes ");
            HashMap map = new HashMap();
        
            Object ob1 = new CODataEqualsHC(10,"d1");
            Object ob2 = new CODataEqualsHC(10,"d2"); //same hashcode but diferent data
            Object ob3 = ob1;
            Object ob4 = new CODataEqualsHC(21,"d3");

            map.put(ob1, " its is ob1-d1");
            map.put(ob2, " its is ob2-d2 ");
            map.put(ob4, " its is ob4-d2 ");

            System.out.println(" map.get(ob3)"+map.get(ob3)); //it is ob1 -gets value- since key is ob1 which is same instance of ob3
            System.out.println(" map.get(ob2)"+map.get(ob2)); //it is ob1. key data is "d1" and hashcode is 10. Able to identify the bucket based on hashcode. Then checks for equals of key. since key data is same ("d1"). It returns true and value is fetched
            System.out.println(" map.get(ob4)"+map.get(ob4)); // it is ob4. key is same instance gets the value 
        }
    
    }

    
}// JavaObjectAndHashCode
