import java.lang.*;
import java.util.*;
import java.net.*;
import com.adventnet.snmp.snmp2.*;
public class SimpleTrapGenerator
{
    //This API is used to generate trap. We should give four arguments to this class .
    //First argument is the "peername" ie..The name of the agent to which we are  sending the Trap.
    //Second argument is the port to which we send the traps to the agent.
    //Third argument is the number of traps to be sent.
    //Sleeping time between the traps in milliseconds.
  public static void main(String args[])
  {
      if(args.length!=4)
      {
        System.out.println("Usage: java genTrap PeerName PortNumber No_Of_Traps TimeInterval(milliseconds)");
        System.exit(0);
      }
      long sendCount = 0;
      try
      {
          SnmpAPI  api;
          api = new SnmpAPI();
          api.start();
          SnmpSession session = new SnmpSession(api);
          session.setPeername(args[0]);
          session.setCommunity("public");
          session.setRemotePort(Integer.parseInt(args[1]));
          session.open();
          SnmpPDU pdu = new SnmpPDU();
          System.out.println("Time : " + System.currentTimeMillis() + " Traps sent : " + sendCount);
          for(int i =0;i<Integer.parseInt(args[2]);i++)
          {
              //SnmpPDU pdu = new SnmpPDU();
              pdu.setCommand( api.TRP_REQ_MSG );
              pdu.setEnterprise(new SnmpOID(".1.3.6.1.2.1.11"));
              pdu.setAgentAddress(InetAddress.getByName("localhost"));
              pdu.setTrapType(2);
              pdu.setSpecificType(0);
              pdu.setUpTime(0);
              SnmpVar var = null;
              SnmpVarBind varbind = new SnmpVarBind(new SnmpOID(".1.3.6.1.2.1.11"), new SnmpInt(i));
              pdu.addVariableBinding(varbind);
              Thread.sleep(Integer.parseInt(args[3]));
              session.send(pdu);
              // the below print will give the time in milliseconds at which the trap pdu has been send.
              sendCount++;
          }
          System.out.println("Time : " + System.currentTimeMillis() + " Traps sent : " + sendCount);
          Thread.sleep(2000);
          System.exit(0);
      }
      catch(NumberFormatException e)
          {
              System.out.println("Number Format Exception occured in input.\n Usage: java genTrap PeerName PortNumber No_Of_Traps TimeInterval(milliseconds)");
              System.exit(0);
          }
      catch(Exception e)
          {
              System.out.println("The Exception is"+e);
              System.out.println("Total send is : " + sendCount);
              System.exit(0);
          }
  }
    
}

