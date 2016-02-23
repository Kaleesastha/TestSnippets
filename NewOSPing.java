public class NewOSPing{
    public static void main(String[] s)
    {   
            //while(true)
            //{   
                        try 
                        {   
                                String host = s[0];
                                long currentTime = System.currentTimeMillis();
                                Runtime.getRuntime().exec("ping -c 1 "+host);
                                System.out.println("ping took : "+(System.currentTimeMillis()-currentTime) +" milliseconds");
                                System.out.println("############################################");
                        }   
                        catch(Exception e)
                        {   
                                e.printStackTrace();
                        }   
               // }   
    }   
}

