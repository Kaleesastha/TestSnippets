/***********************************************************************************************
This is a test program to test Proper exception propagation for userTransaction operations in map api methods. This is run as a process whil starting nms.
Sadhiesh Babu.D  23/04/2002
************************************************************************************************/

package test;
import java.rmi.*;
import java.util.*;

import javax.transaction.*;
import javax.transaction.RollbackException;
import com.adventnet.management.transaction.TransactionAPI;

import com.adventnet.management.transaction.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.store.*;



public class MapTransTest implements RunProcessInterface
{

    MapAPI api = null;
    static boolean firstTime = true;
    static boolean secondTime = true;
    static boolean initialized = false;
    TransactionAPI transAPI= null;

    public MapTransTest()
    {

    }
    
    public void transaction(MapAPI api,TransactionAPI transAPI)
    {
         if(firstTime)
         {  
	    firstTime = false;
            transAPI = NmsUtil.relapi.getTransactionAPI();
            if(transAPI == null) 
            {
                System.out.println("COULD NOT GET TRANSACTIONAPI ");
            }

         /*  //////////////////////////////////////////////////////////////////////////////////
             case1:  AddContainer(Mapkey,props)
         //////////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction"+e);               
	       }
               
               Properties p=new Properties();
               p.setProperty("topology","grid,star");
               p.setProperty("name","container1");
               Thread.sleep(18000);  
               api.addContainer("Container1",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 1");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case1 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 1: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 1");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING 1:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING 1 :"+e_all1);
               e_all1.printStackTrace();
            } 
      
        /*  //////////////////////////////////////////////////////////////////////////////////
             case2:  AddCustomMap(Mapname,Mapprop,customprop)
         //////////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction2"+e);               
	       }
               
	       Properties p = new Properties();
               p.put("name","map1");
               Properties p1=new Properties();
               p1.put("isSNMP","true");
               Thread.sleep(25000);     
               api.addCustomMap("map.netmap",p,p1);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 2");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case1 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 2: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 2");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case2:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case2:"+e_all1);
               e_all1.printStackTrace();
            } 
        /*  //////////////////////////////////////////////////////////////////////////////////
             case3:   AddCustomMap(Mapname,Mapprop,customprop,index)
         //////////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction"+e);               
	       }
               
               Properties p = new Properties();
               p.put("name","map1");
               Properties p1=new Properties();
               p1.put("isSNMP","true");
               Thread.sleep(25000);     
               api.addCustomMap("map.netmap",p,p1,3);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 3");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case3 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 3: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 3");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case3:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case3:"+e_all1);
               e_all1.printStackTrace();
            } 
      
        /*  //////////////////////////////////////////////////////////////////////////////////
             case4:   AddGroup(Mapkey,prop)
         //////////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction4"+e);               
	       }
               
               Properties p = new Properties();
               p.put("name","group1");
               Thread.sleep(25000);     
               api.addGroup("group1",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 4");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case4 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 4: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 4");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case4:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case4:"+e_all1);
               e_all1.printStackTrace();
            } 
      
            /*  ////////////////////////////////////////////////////////////////////////////////
                case5:  AddLink(mapkey,props)
                /////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction5"+e);               
	       }
               Properties p = new Properties();
               p.put("name","link11");
               Thread.sleep(25000);     
               api.addLink("link1",p);

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 5");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case5 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 5: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 5");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case5:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case5:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ///////////////////////////////////////////////////////////////////////////////
                case6:  AddMap(mapname,props)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction6"+e);               
	       }
               Properties p = new Properties();
               p.put("name","map1");
               /*               Properties p1=new Properties();
                                p1.put("isSNMP","true");*/
               Thread.sleep(25000);     
               api.addMap("map.netmap",p);

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 6");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case6 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 6: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 6");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case6:"+e_all);
               e_all.printStackTrace();
            } 
  	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case6:"+e_all1);
               e_all1.printStackTrace();
            } 
            /*  ///////////////////////////////////////////////////////////////////////////////
                case7:  AddMap(mapname,props,index)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction7"+e);               
	       }
               Properties p = new Properties();
               p.put("name","map1");
               /*               Properties p1=new Properties();
                                p1.put("isSNMP","true");*/
               Thread.sleep(25000);     
               api.addMap("map.netmap",p,2);

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 7");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case7 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 7: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 7");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case7:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case7:"+e_all1);
               e_all1.printStackTrace();
            } 
            /*  ///////////////////////////////////////////////////////////////////////////////
                case8:  AddObjects(classname,vector mapobj)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction8"+e);               
	       }
               Vector symb=new Vector();
               MapSymbol ms1=new MapSymbol();
               ms1.setName("symbol1");
               symb.addElement(ms1);
               MapSymbol ms2=new MapSymbol();
               ms2.setName("symbol2");
               symb.addElement(ms2);
               MapSymbol ms3=new MapSymbol();
               ms3.setName("symbol3");
               symb.addElement(ms3);
               Thread.sleep(25000); 
               api.addObjects("MapSymbol",symb);

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 8");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case8 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 8: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 8");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case8:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case8:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ///////////////////////////////////////////////////////////////////////////////
                case9:  AddSymbol(mapkey,prop)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction9"+e);               
	       }
               
               Properties p = new Properties();
               p.put("name","symb1");
               Thread.sleep(25000); 
               api.addSymbol("Symbol",p);

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 9");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case9 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 9: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 9");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case9:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case9:"+e_all1);
               e_all1.printStackTrace();
            } 
            /*  ///////////////////////////////////////////////////////////////////////////////
                case10:  doesTheMapExist(mapkey)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction10"+e);               
	       }

               Thread.sleep(25000); 
               api.doesTheMapExist("ipnet.netmap");

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 10");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 10 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 10: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 10");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case10:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case10:"+e_all1);
               e_all1.printStackTrace();
            } 
            /*  ///////////////////////////////////////////////////////////////////////////////
                case11:  getAllSymbolProperties(mapname)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction11"+e);       
	       }

               Thread.sleep(25000); 
               api.getAllSymbolProperties("ipnet.netmap");

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 11");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 11 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 11: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 11");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case11:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case11:"+e_all1);
               e_all1.printStackTrace();
            } 
          
            /*  ///////////////////////////////////////////////////////////////////////////////
                case12:  getCompleteList()
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction12"+e);       
	       }
               Thread.sleep(25000); 
               api.getCompleteList();

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 12");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 12 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 12: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 12");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case12:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case12:"+e_all1);
               e_all1.printStackTrace();
            } 
            /*  ///////////////////////////////////////////////////////////////////////////////
                case13:  getCustomMapNames()
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction13"+e);       
	       }
               Thread.sleep(25000); 
               Vector maps=api.getCustomMapNames();
               //               System.out.println("map at 1:"+maps.elementAt(1));
               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 13");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 13 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 13: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 13");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case13:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case13:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ///////////////////////////////////////////////////////////////////////////////
                case14:  getDefaultmapNames()
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction14"+e);       
	       }
               Thread.sleep(25000); 
               Vector maps=api.getDefaultMapNames();
               //               System.out.println("map at 1:"+maps.elementAt(1));


               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 14");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 14 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 14: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 14");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case14:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case14:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ///////////////////////////////////////////////////////////////////////////////
                case15:  getLinkProperties(mapkey,name)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction15"+e);       
	       }
               Thread.sleep(25000); 
               api.getLinkProperties("ipnet.netmap","link1");

               transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 15");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 15 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 15: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 15");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING for case15:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING for case15:"+e_all1);
               e_all1.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                case16:  getMap(mapkey)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction16"+e); 
               }

               Thread.sleep(25000);  
               api.getMap("ipnet.netmap");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 16");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 16 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 16: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 16");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 16:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 16:"+e_all1);
               e_all1.printStackTrace();
            } 
           
            /*  ////////////////////////////////////////////////////////////////////////////////
                case17:  getMapNames()
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction17"+e); 
               }

               Thread.sleep(25000);  
               api.getMapNames();

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 17");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 17 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 17: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 17");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 17:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE deleting case 17:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ////////////////////////////////////////////////////////////////////////////////
                case18:  getObject()
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction18"+e); 
               }

               Thread.sleep(21000);  
               api.getObject("sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 18");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 18 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 18: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 18");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 18:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
                {
                    System.out.println("EXCEPTION WHILE deleting case 18:"+e_all1);
                    e_all1.printStackTrace();
                } 
        
            
             /*  ////////////////////////////////////////////////////////////////////////////////
                case19:  getObjectNamesWithProps()
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction19"+e); 
               }
               Properties p=new Properties();
               p.put("name","sadhieshb");
               Thread.sleep(21000);  
               api.getObjectNamesWithProps(p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 19");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 19 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 19: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 19");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 19:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE deleting case 19:"+e_all1);
               e_all1.printStackTrace();
            } 
                
                
            /*  ////////////////////////////////////////////////////////////////////////////////
                  case20:  getObjects(classname,props)
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction20"+e); 
               }
               Properties p=new Properties();
               p.put("name","sadhieshb");
               Thread.sleep(21000);  
               Vector symb=api.getObjects("MapSymbol",p);
               System.out.println("syms at 1"+symb.elementAt(1));
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 20");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 20 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 20: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 20");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 20:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE deleting case 20:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case21:  getPropertiesOfObject(obj)
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction21"+e); 
               }

               Thread.sleep(22000);  
               api.getPropertiesOfObject("sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 21");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 21 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 21: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 21");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 21:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE deleting case 21:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case22: getSymbolNames(map)
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction22"+e); 
               }

               Thread.sleep(21000);  
               api.getSymbolNames("ipnet.netmap");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 22");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 22 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 22: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 22");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE deleting case 22:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE deleting case 22:"+e_all1);
               e_all1.printStackTrace();
            } 



            /*  ////////////////////////////////////////////////////////////////////////////////
                case23: getSymbolNamesAssociatedWithObject(objname);
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction23"+e); 
               }
               
               Thread.sleep(21000);  
               api. getSymbolNamesAssociatedWithObject("sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 23");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 23 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 23: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 23");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 23:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 23:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ////////////////////////////////////////////////////////////////////////////////
                case24:   getSymbolNamesAssociatedWithObject(,type,objname);
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction24"+e); 
               }
               
               Thread.sleep(25000);  
               api.getSymbolNamesAssociatedWithObject("symbol","sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 24");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 24 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 24: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 24");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 24:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 24:"+e_all1);
               e_all1.printStackTrace();
            } 
                  
            /*  ////////////////////////////////////////////////////////////////////////////////
                case25:  getSymbolProperties(mapname,name)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction25"+e); 
               }
               
               Thread.sleep(25000);  
               api.getSymbolProperties("192.168.9.0.netmap","sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 25");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 25 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 25: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 25");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println(" user EXCEPTION WHILE ADDING case 25:"+e_all);
               e_all.printStackTrace();
            } 
 	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 25:"+e_all1);
               e_all1.printStackTrace();
            } 
            
            /*  ////////////////////////////////////////////////////////////////////////////////
                case26:  updateContainer(mapkey,props)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction26"+e); 
               }
               
               Properties p =new Properties();
               p.put("name","container1");
               p.put("topology","grid,star");
               Thread.sleep(25000);  
               api.updateContainer("Switches.netmap",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 26");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 26 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 26: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 26");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 26:"+e_all);
               e_all.printStackTrace();
            } 
  	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 26:"+e_all1);
               e_all1.printStackTrace();
            } 
                  
            /*  ////////////////////////////////////////////////////////////////////////////////
                case27:  updateGroup(map,prop)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction27"+e); 
               }
               
               Properties p =new Properties();
               p.put("name","Group1");
               p.put("iconname","grp.png");
               Thread.sleep(25000);  
               api.updateGroup("Switches.netmap",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 27");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 27 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 27: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 27");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 27:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 27:"+e_all1);
               e_all1.printStackTrace();
            } 
      
                  
            /*  ////////////////////////////////////////////////////////////////////////////////
                case28:  updateLink(map,prop)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction28"+e); 
               }
               
               
               Properties p =new Properties();
               p.put("name","Link1");
               p.put("iconname","link.png");
               Thread.sleep(25000);  
               api.updateLink("Switches.netmap",p);

 
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 28");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 28 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 28: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 28");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 28:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 28:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                case29:  updatemap(map,prop)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(15000);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction29"+e); 
               }
               
               Properties p =new Properties();
               p.put("name","map1");
               p.put("iconname","map.png");
               Thread.sleep(20000);  
               api.updateMap("Switches.netmap",p);

 
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 29");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 29 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 29: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 29");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 29:"+e_all);
               e_all.printStackTrace();
            } 
	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 29:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                case30:  updatemap(map,prop,cust prop)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction30"+e); 
               }
               
              
               Properties p =new Properties();
               p.put("name","map1");
               p.put("iconname","map.png");
               Properties p1 =new Properties();
               p1.put("isSNMP","true");
               Thread.sleep(25000);  
               api.updateMap("Switches.netmap",p,p1);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 30");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 30 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 30: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 30");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 30:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 30:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                case31:  updateSymbol(mapkey,props)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction31"+e); 
               }
                             
               Properties p1 =new Properties();
               p1.put("name","Symbol");
               Thread.sleep(25000);  
               api.updateSymbol("Switches.netmap",p1);
    
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 31");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 31 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 31: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 31");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 31:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 31:"+e_all1);
               e_all1.printStackTrace();
            } 
      
            /*  ////////////////////////////////////////////////////////////////////////////////
                case32:  deleteCointainer(map,name)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction32"+e); 
               }
               
               Thread.sleep(25000);  
               api.deleteContainer("192.168.9.0.netmap","sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 32");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 32 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 32: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 32");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 32:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 32:"+e_all1);
               e_all1.printStackTrace();
            } 
                              
            /*  ////////////////////////////////////////////////////////////////////////////////
                case33:  deletGroup("map,name")
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction33"+e); 
               }
               
               Thread.sleep(25000);  
               api.deleteGroup("ipnet.netmap","group1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 33");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 34 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 33: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 33");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("userEXCEPTION WHILE ADDING case 33:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 33:"+e_all1);
               e_all1.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                case34:  deleteLink(map,name)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction34"+e); 
               }
               
               Thread.sleep(25000);  
               api.deleteLink("ipnet.netmap","link1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 34");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 34 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 34: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 34");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 34:"+e_all);
               e_all.printStackTrace();
            } 
 	    catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 34:"+e_all1);
               e_all1.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                case35:  deletemap(mapname)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction35"+e); 
               }
               
               Thread.sleep(25000);  
               api.deleteMap("Printers.netmap");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 35");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 35 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 35: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 35");
               }      
            }  
            catch(UserTransactionException e_all1)
                {
                    System.out.println("User EXCEPTION WHILE ADDING case 35:"+e_all1);
                    e_all1.printStackTrace();
                }      	 
            catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 35:"+e_all);
               e_all.printStackTrace();
            } 
         
                                                            
            /*  ////////////////////////////////////////////////////////////////////////////////
                case36:  deleteSymbol(map,name)
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction36"+e); 
               }
               
               Thread.sleep(25000);  
               api.deleteSymbol("192.168.9.0.netmap","sadhieshb");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 36");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 36 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 36: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 36");
               }      
            }  
	    catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 36:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 36:"+e_all);
               e_all.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                case 37:  update map after add map
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction37(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction37(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","map1.netmap");
               p.put("iconname","new1.png");
               api.addMap("map1.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new2.png");
               p1.put("name","map1.netmap");
               Thread.sleep(25000);  
               api.updateMap("map1.netmap",p1);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 37");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 35 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 37: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 37");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 37:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 37:"+e_all);
               e_all.printStackTrace();
            } 
               

            /*  ////////////////////////////////////////////////////////////////////////////////
                case 38:  delete map after add map
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction38(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction38(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","map1.netmap");
               p.put("iconname","new1.png");
               api.addMap("map1.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","map1.netmap");
               Thread.sleep(25000);  
               api.deleteMap("map1.netmap");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 38");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 38 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 38: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 38");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 38:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 38:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                case39:  delete after update
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------


               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction39(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction39(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","map1.netmap");
               p.put("iconname","new1.png");
               api.addMap("map1.netmap",p);
               api.updateMap("map1.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","map1.netmap");
               Thread.sleep(25000);  
               api.deleteMap("map1.netmap");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 39");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 39 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 39: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 39");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 39:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 39:"+e_all);
               e_all.printStackTrace();
            } 
                                                            
            /*  ////////////////////////////////////////////////////////////////////////////////
                case40:  updatesymbol after add symbol
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction40(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction40(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","symb1");
               p.put("iconname","new1.png");
               api.addSymbol("map1.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new2.png");
               p1.put("name","symb1");
               Thread.sleep(25000);  
               api.updateSymbol("map1.netmap",p1);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 40");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 40 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 40: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 40");
               }      
            }  
     	    catch(UserTransactionException e_all)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 40:"+e_all);
               e_all.printStackTrace();
            } 
            catch(Exception e_all1)
            {
               System.out.println("EXCEPTION WHILE ADDING case 40:"+e_all1);
               e_all1.printStackTrace();
            } 
                                                                                                                                                                          
            /*  ////////////////////////////////////////////////////////////////////////////////
                case41:  delete symb after add
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction41(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction41(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","symb1");
               p.put("iconname","new1.png");
               api.addSymbol("map1.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","symb1");
               Thread.sleep(25000);  
               api.deleteSymbol("map1.netmap","symb1");


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 41");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 41 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 41: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 41");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 41:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 41:"+e_all);
               e_all.printStackTrace();
            } 
                                       
                                                                         
            /*  ////////////////////////////////////////////////////////////////////////////////
                case42:  delete symb after update
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------


               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction42(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction42(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","symb1");
               p.put("iconname","new1.png");
               api.addSymbol("ipnet.netmap",p);
               api.updateSymbol("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","symb1");
               Thread.sleep(25000);  
               api.deleteSymbol("ipnet.netmap","symb1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 42");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 42 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 42: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 42");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 42:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 42:"+e_all);
               e_all.printStackTrace();
            }

            /*  ////////////////////////////////////////////////////////////////////////////////
                case43:  update link after add
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction43(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction43(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","link1");
               p.put("iconname","new1.png");
               api.addLink("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new2.png");
               p1.put("name","link1");
               Thread.sleep(25000);  
               api.updateLink("ipnet.netmap",p1);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 43");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 43 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 43: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 43");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 43:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 43:"+e_all);
               e_all.printStackTrace();
            }


            /*  ////////////////////////////////////////////////////////////////////////////////
                case44: delete link after add
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction44(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction44(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","link1");
               p.put("iconname","new1.png");
               api.addLink("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","link1");
               Thread.sleep(25000);  
               api.deleteLink("ipnet.netmap","link1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 44");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 44 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 44: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 44");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 44:"+e_all1);
               e_all1.printStackTrace();
            }

     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 44:"+e_all);
               e_all.printStackTrace();
            }


            /*  ////////////////////////////////////////////////////////////////////////////////
                case45:  delete link after update
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction45(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction45(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","link1");
               p.put("iconname","new1.png");
               api.addLink("ipnet.netmap",p);
               api.updateLink("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","link1");
               Thread.sleep(25000);  
               api.deleteLink("ipnet.netmap","link1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 45");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 45 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 45: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 45");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 45:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 45:"+e_all);
               e_all.printStackTrace();
            }

            /*  ////////////////////////////////////////////////////////////////////////////////
                case46:  updateContainer after add container
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction46(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction46(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","Container1");
               p.put("iconname","new1.png");
               api.addContainer("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","Container1");
               Thread.sleep(25000);  
               api.updateContainer("ipnet.netmap",p1);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 46");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 46 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 46: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 46");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 46:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 46:"+e_all);
               e_all.printStackTrace();
            }


            /*  ////////////////////////////////////////////////////////////////////////////////
                case47:  delete container after add
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------

               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction47(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction47(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","Container1");
               p.put("iconname","new1.png");
               api.addContainer("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","Container1");
               Thread.sleep(25000);  
               api.deleteContainer("ipnet.netmap","container1");
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 47");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 47 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 47: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 47");
               }      
            }  
	    catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 47:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 47:"+e_all);
               e_all.printStackTrace();
            }


            /*  ////////////////////////////////////////////////////////////////////////////////
                case48:  delete container after updatecontainer
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
                try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction48(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction48(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","Container1");
               p.put("iconname","new1.png");
               api.addContainer("ipnet.netmap",p);
               api.updateContainer("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","Container1");
               Thread.sleep(25000);  
               api.deleteContainer("ipnet.netmap","Container1");
     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 48");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 48 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 48: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 48");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 48:"+e_all1);
               e_all1.printStackTrace();
            }

     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 48:"+e_all);
               e_all.printStackTrace();
            }



            /*  ////////////////////////////////////////////////////////////////////////////////
                case49:  update group after add group
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
        
                try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction49(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction49(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","group1");
               p.put("iconname","new1.png");
               api.addGroup("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","group1");
               Thread.sleep(25000);  
               api.updateGroup("ipnet.netmap",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 49");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 49 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 49: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 49");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 49:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 49:"+e_all);
               e_all.printStackTrace();
            }

         
            /*  ////////////////////////////////////////////////////////////////////////////////
                case 50 :   Delete group after add group
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
                       try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction50(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction50(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","group1");
               p.put("iconname","new1.png");
               api.addGroup("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","group1");
               Thread.sleep(25000);  
               api.deleteGroup("ipnet.netmap","group1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 50");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 50 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 50: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 50");
               }      
            }  
	    catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 50:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 50:"+e_all);
               e_all.printStackTrace();
            }


            /*  ////////////////////////////////////////////////////////////////////////////////
                case 51 :   Delete group after update group
                ////////////////////////////////////////////////////////////////////////////  */
            try
            {
                   //----transaction starts ------------
                       try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction51(1)"+e); 
               }
               try
               {
          	 transAPI.begin();
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while begiining transaction51(2)"+e); 
               }
               Properties p=new Properties();
               p.put("name","group1");
               p.put("iconname","new1.png");
               api.addGroup("ipnet.netmap",p);
               api.updateGroup("ipnet.netmap",p);
               transAPI.commit();

               Properties p1=new Properties();
               p1.put("iconname","new1.png");
               p1.put("name","group1");
               Thread.sleep(25000);  
               api.deleteGroup("ipnet.netmap","group1");

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 51");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 51 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 51: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 51");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE ADDING case 51:"+e_all1);
               e_all1.printStackTrace();
            }
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE ADDING case 51:"+e_all);
               e_all.printStackTrace();
            }

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case52: addsymbol invalid setownername
               ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction52"+e); 
               }
               Properties p =new Properties();
               p.put("name","symbolex");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.addSymbol("ipnet.netmap",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 52");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 52 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 52: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 52");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 52:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 52:"+e_all);
               e_all.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                  case53: updateSymbol with invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshb");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.updateSymbol("192.168.9.0.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 53");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 53 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 53: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 53");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 53:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 53:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case54:  addmap with inavlid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
        
                try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction54"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshb.netmap");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.addMap("sadhieshb.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 54");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 54 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 54: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 54");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 54:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 54:"+e_all);
               e_all.printStackTrace();
            } 



            /*  ////////////////////////////////////////////////////////////////////////////////
                  case55:  updatemap with inavlid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
        
                try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction55"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshb.netmap");
               p.put("label","aaaaaaaaaaa");
               api.addMap("sadhieshb.netmap",p);
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.updateMap("sadhieshb.netmap",p);



     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 55");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 55 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 55: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 55");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 55:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 55:"+e_all);
               e_all.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                  case56: addGroup with invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
           try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshbgrp");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.addGroup("ipnet.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 56");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 56 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 56: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 56");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 56:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 56:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case57: updateGroup invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
    try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshbgrp");
               p.put("label","aaaaaaaa");
               api.addGroup("ipnet.netmap",p);
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.updateGroup("ipnet.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 57");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 57 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 57: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 57");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 57:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 57:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case58: addcontainer() with invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
        try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshbcont");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.addContainer("ipnet.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 58");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 58 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 58: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 58");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 58:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 58:"+e_all);
               e_all.printStackTrace();
            } 


            /*  ////////////////////////////////////////////////////////////////////////////////
                  case59:  updatecontainer invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
       
    try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshbcont");
               p.put("label","aaaaaaaaaaaaaaaaaaaa");
               api.addContainer("ipnet.netmap",p);
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.updateContainer("ipnet.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 59");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 59 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 59: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 59");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 59:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 59:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case60:  addLink() with inavlid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
          try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshblink");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.addLink("ipnet.netmap",p);

     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 60");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 60 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 60: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 60");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 60:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 60:"+e_all);
               e_all.printStackTrace();
            } 

            /*  ////////////////////////////////////////////////////////////////////////////////
                  case61:  update link invalid value
                ////////////////////////////////////////////////////////////////////////////  */
                try
            {
                   //----transaction starts ------------
               try
               {
          	 transAPI.begin(-1);
               }
               catch(Exception e)
               {
                  System.out.println(" Exception while beginning transaction53"+e); 
               }
             
               Properties p =new Properties();
               p.put("name","sadhieshblink");
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaa");
               p.put("mapname","sadhiesh.netmap");
               api.addLink("ipnet.netmap",p);
               p.put("label","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
               api.updateLink("ipnet.netmap",p);


     	       transAPI.commit();
            }      
            catch(RemoteException ree)
            {
               if( (ree.detail != null) &&(ree.detail instanceof RollbackException))
               {
                  System.out.println("Before ROLLING BACK for case 61");
                  try
                  {
                     transAPI.rollback("Transaction started by DiscoFilter for case 61 has been Rolledback.."+ree);
                  }
                  catch(Exception un)
                  {
                     System.out.println(" Unable to rollback case 61: "+un);
                     un.printStackTrace();
                  }
                  System.out.println("Transaction rolled back in disc filter for case 61");
               }      
            }  
            catch(UserTransactionException e_all1)
            {
               System.out.println("user EXCEPTION WHILE deleting case 61:"+e_all1);
               e_all1.printStackTrace();
            } 
     	    catch(Exception e_all)
            {
               System.out.println("EXCEPTION WHILE deleting case 61:"+e_all);
               e_all.printStackTrace();
            } 


         }
    }
    public boolean isInitialized()
    {
        return initialized;
    }
    public void shutDown()
    {
    }
    public void callMain(String[] args)
    {
        System.out.println("inside TRANSACTION TEST CALLMAIN()");
        initialized=true;
        try{
            while(api==null)
                {
                    api=(MapAPI)NmsUtil.getAPI("MapAPI");
                    if(api==null)
                        {
                            Thread.sleep(1000);
                        }
                }
        }catch(Exception e)
            {                    
                System.out.println("map"+e.toString());
            }
        try{
            while(transAPI==null)
                {
                    transAPI = NmsUtil.relapi.getTransactionAPI();
                    if(transAPI == null) 
                        {
                            Thread.sleep(1000);
                        }
                }
        }catch(Exception e)
            {                    
                System.out.println("transaction"+e.toString());
            }
        transaction(api,transAPI);  
    }
}
