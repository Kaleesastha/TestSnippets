//$Id: RecoverLostTrapFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $


package test;

import java.util.*;
import java.rmi.Naming;
import com.adventnet.nms.eventdb.TrapAPI;
import com.adventnet.nms.eventdb.TrapFilter;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVarBind;

/**
 * Copyright (c) AdventNet Inc., 2001. All Rights Reserved
 * file:      RecoverLostTrapFilter.java
 * It is designed to Recover lost traps, If any traps
 * were lost while sending from agent to manager(NMS). 
 * It is possible that trap can be lost while sending 
 * from agent to Manager(NMS).
 * It compares the index of Last trap received by NMS
 * to the index of the latest trap sent by agent. If 
 * no traps are lost it will process the traps further.
 * If any traps sent from agent are missing then it will
 * inform the agent and lost trap will be retrieved by
 * requesting the agent to send the lost traps. 
 * This is just an example file. A user need to develop
 * or implement his codes based on the requirement. 
 * @author    Hemant Jatav.x
 */

public class RecoverLostTrapFilter implements TrapFilter 
{

    private int indexOfVarBind;          // The index of the sequence number variable binding 
                                         // in the trap.
                                         // The End user need to make sure that a trap sent by 
                                         // agent has a variable binding which 
                                         // stores the index of trap.This can be achieved by
                                         // configuring the agent

    private int indexOfLastTrap;         // Index of the Last Trap Recieved by Manager. 

    private int indexFromPdu;            // Index of the trap sent by Agent.

    Hashtable agentWithTrapIndex = null; // Stores name of the agent as key against 
                                         //index of Last Trap recieved by manager(NMS)  
    
    /** Default Constructor */
  public RecoverLostTrapFilter()  
  {
      agentWithTrapIndex = new Hashtable();
  }

    /** It gets called whenever a new trap of specified criteria in trap.filters file  
     *  in <WebNMS home> conf directory  is received by the NMS server.
     *  @param trapPdu SnmpPDU sent by Agent.
     *  @return Object.
     */

  public Object applyTrapFilter(SnmpPDU trapPdu)
  {
  try
    {
       String agentName = trapPdu.getRemoteHost();
       indexFromPdu = getTrapIndexFromPdu(trapPdu);
       boolean indexMatched = setLastTrapIndex(agentName,indexFromPdu);
       if(indexMatched)
        {
            // Once it is confirmed that none of the traps recieved from agent is lost. 
            // Then End user can perform any action on the traps recieved from agent.
            // The trapPdu can be return as it is and the further processing of trapPdu
            // could be left to the trap filters defined in NMS. End User can as well 
            // take the required action or can perform an action of his choice like 
            // returning an event of his defined properties.

           return trapPdu;
        }
    }
  catch(Exception e)
    {
       e.printStackTrace();
    }

  // Once it is confirmed that some of the traps recieved from agent is lost.
  // Consider an example like sequence of traps sent from an agent is 1,2,3,6,7,8,9
  // Note that here traps of sequence no. 4,5 and 6 are missing. As soon as it is 
  // that traps of sequence no. 4,5,6 is missing We are not interested in processing 
  // any traps recieved after sequence no. 3 hence returning the null here.
  // Once the missing traps sequece is discovered, End User can write the method 
  // recoverLostTraps() according to his requirements and retrieves the lost traps 
  // from agent,and traps from sequence no. 4 to 9 can be processed.
  
  return null;

  }

   /** Called from applyTrapFilter(). Determines if any traps were lost by comparing 
    *  the indexFromPdu  with the indexOfLastTrap  stored in Hashtable. If any traps
    *  were lost while sending traps from agent to manager it informs the agent 
    *  the agent about the lost traps, and retrieves traps. 
    *  @param   agent Name of the agent from where traps are recieved.
    *  @param   indexOfTrap Latest index of Trap recieved from Agent. 
    *  @return  boolean true if no trap is lost,false if any traps were lost.
    */
   
   private boolean setLastTrapIndex(String name,int currentTrapIndex)
   {
    if(agentWithTrapIndex.containsKey(name))
     {
         indexOfLastTrap = ((Integer)agentWithTrapIndex.get(name)).intValue();
         if(currentTrapIndex == indexOfLastTrap+1)
          {
              indexOfLastTrap = currentTrapIndex;
              agentWithTrapIndex.put(name,new Integer(currentTrapIndex));
              return true;
          }
         else
          {
              System.out.println(" Trap is lost and Last Trap No. is " + indexOfLastTrap);
              System.out.println(" Total No. of Trap lost are "+(currentTrapIndex-indexOfLastTrap));
                  
                     // WARNING: INCOMPLETE CODE. End user need to implement his code 
                     // Based on his requirement.

                     // Inform the agent about  missing traps sequence and retrieves the  
                     // lost traps 
                     //recoverLostTraps() 
          }
         return false;
     } 
    else
     {
         agentWithTrapIndex.put(name,new Integer(currentTrapIndex));
     }
    return true;
   }
 
    /** Called from applyTrapFilter(). Determines the index of the trap sent by Agent.
     *  @param pdu SnmpPDU sent by Agent.
     *  @return int returns the index of the trap sent by agent.
     */

  private int getTrapIndexFromPdu(SnmpPDU pdu)
   {
      Vector varBindVector = pdu.getVariableBindings();
      int tempInt = 0;
      for(int i=0;i<varBindVector.size();i++)
          {
              SnmpVarBind bind = (SnmpVarBind)varBindVector.elementAt(i);          
              if((bind.getObjectID().toString()).equals(".x.x.x.x.x.x"))
                  {
                      indexOfVarBind = i;
                      //  If the oid in Variable bindings is equal to .x.x.x.x.x, 
                      //  then fetch the value of the var bind.
                      tempInt = ((Integer)pdu.getVariable(indexOfVarBind).toValue()).intValue();
                  }
          }
      return tempInt;
   }


    /** This method is invoked when the NMS lost any traps.It is determined by
     *  comparing the indexOfLast trap received to the indexFromPdu of the latest 
     *  Trap sent by agent.The End user need to write this method according to his
     *  requirement. Once the lost traps has been retrieved,it can be processed further.
     *  @param End user has to determine,according to his requirement and his code 
     *  implementation.
     */
     public void recoverLostTraps()
     {
       // Should be written by end user Based on the requirement.

     }
}
