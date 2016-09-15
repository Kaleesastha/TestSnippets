/* $Id: prov_org_config_act_tester.java,v 1.1 2006/02/18 06:23:46 sasidhar Exp $ */

import java.rmi.*;
import java.util.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;
import java.io.*;

public class prov_org_config_act_tester 
{
	public static void main(String args[])
	{	
		if(args.length < 2)
		{
			System.out.println("Path of WebNMS_Home and host in which NMSServer is running is not set USAGE:: java prov_org_config_act_Tester <path of WebNMS Home > <NMSServer> ");
			return;
		}

		String WebNMS_Home=new File(args[0]).getAbsolutePath().replace(File.separatorChar, '/');
		BufferedWriter brout1=null,brout = null;
		BufferedReader brin= null;
		try
		{
			ProvisioningAPI provisioningAPI=null;
			String apiString =  "//"+args[1]+"/ProvisioningAPI";
			// hostName specifies name of the host in which Web NMS Server is running.
			provisioningAPI = (ProvisioningAPI) Naming.lookup(apiString);

			if(provisioningAPI!= null)
				System.out.println("ProvisioningAPI is successfully accessed" );        

			brin = new BufferedReader(new FileReader("prov_org_config_act_Testinput.txt")); //input file

			//if the user wants to generate master output uncomment the below line and comment the line next to it.

			//brout = new BufferedWriter(new FileWriter("prov_org_config_act_tester_Mastertestoutput.txt")); 
			brout = new BufferedWriter(new FileWriter("prov_org_config_act_Testoutput.txt"));

			String in="";
			String[] list,s1,s2;

			while ((in=brin.readLine()) != null)
			{
				s1=in.split(":");
				String templateparam,template = null;
				TemplateParams templateParams = new TemplateParams();
				Properties prop = new Properties();
				if (s1[1].startsWith("<WebNMS Home>"))
				{
					s1[1]=s1[1].replaceFirst("<WebNMS Home>",WebNMS_Home);
				}

				if(s1.length == 2)
				{      
					brout.write("\n"+s1[0]+"\tRESULT:");
					try
					{	
						if ((s1[1].trim()).equals("null")) 
						{
							try
							{
								list = provisioningAPI.getTemplateList(null);
							}
							catch (IllegalArgumentException ex)
							{
								brout.write("IllegalArgumentException "+ ex.getMessage());
							}
						}
						else
						{
							list = provisioningAPI.getTemplateList(s1[1]);
							if(list != null)
							{       Arrays.sort(list);
								for(int i = 0; i < list.length; i++)
								{
									brout.write(list[i]+"\t");
								}
							}
							else
							{
								brout.write("null");
							}
						}

					}
					catch(IllegalArgumentException ex)
					{
						brout.write("IllegalArgumentException Invalid Path ");
					}
				}
				if(s1.length == 3)
				{
					try
					{			
						brout.write("\n"+s1[0]+"\tRESULT:");
						if ((s1[1].trim()).equals("null")) 
						{
							try
							{
								templateparam = provisioningAPI.getTemplateParameters(null,s1[2]);
							}
							catch (IllegalArgumentException ex)
							{
								brout.write("IllegalArgumentException "+ ex.getMessage());
							}
						}
						else
						{
							templateparam = provisioningAPI.getTemplateParameters(s1[1],s1[2]);
							brout.write(templateparam );
						}
					}
					catch (TemplateNotFoundException ex)
					{

						brout.write("TemplateNotFoundException  No Template exists with the name   "+ s1[2]);
					}
					catch(IllegalArgumentException ex)
					{
						brout.write("IllegalArgumentException Invalid Path ");
					}
				}
				if(s1.length == 4)
				{
					try
					{	
						brout.write("\n"+s1[0]+"\tRESULT:");
						if ((s1[1].trim()).equals("null")) 
						{
							try
							{	
								template = provisioningAPI.getTemplate(null,s1[2],s1[3]); 
							}
							catch (Exception ex)
							{
								brout.write("IllegalArgumentException "+ ex.getMessage());
							}
						}
						else
						{
							if ((s1[3].trim()).equals("null"))	
							{
								template = provisioningAPI.getTemplate(s1[1],s1[2],null);					
							}
							else
							{
								template = provisioningAPI.getTemplate(s1[1],s1[2],s1[3]);								
							}

							brout1=new BufferedWriter(new FileWriter("templates/"+s1[2]+".xml"));
							brout1.write(template);
							brout.write(s1[2]+".xml");

						}
					}
					catch (TemplateNotFoundException ex)
					{

						brout.write("TemplateNotFoundException No Template exists with the name   "+ s1[2]);
					}
					catch (TemplateInitializationException ex)
					{

						brout.write("TemplateInitializationException Getting Template named   "+ s1[2] + "   in the directory" +" with params:" +s1[3]);
					}
					catch(IllegalArgumentException ex)
					{
						brout.write("IllegalArgumentException Invalid Path ");
					}
					finally
					{	try
						{
							if(brout1 !=null)	brout1.close();
						}
						catch(Exception e)
						{
						}
					}

				}
			}

		} 
		catch (Exception e)
		{
			System.out.println("Exception in accesssing the handle to provisioningAPI" );
			e.printStackTrace();
		}
		finally
		{	try
			{
				if(brout !=null)	brout.close();
				if(brin !=null)		brin.close();
			}
			catch(Exception e)
			{
			}
		}


		// Verification of generated test output with the master output

		BufferedReader brin1=null,brin2=null,brin3=null,brin4=null;
		BufferedWriter brout2=null;
		try
		{
			brin1 = new BufferedReader(new FileReader("prov_org_config_act_Mastertestoutput.txt"));
			brin2 = new BufferedReader(new FileReader("prov_org_config_act_Testoutput.txt"));
			brout2= new BufferedWriter(new FileWriter("prov_org_config_act_Testresult.txt"));
			String in1,in2,in3,in4="";
			String[] sin1,sin2;
			System.out.println("Enetered verification");
			in1=brin1.readLine();
			in2=brin2.readLine();
			boolean res=false;

			while ( (((in1=brin1.readLine()) != null) && ((in2=brin2.readLine()) != null)) )   
			{
				res=false;
				sin1=in1.split("RESULT:");
				sin2=in2.split("RESULT:");
				if(!(sin1[1].endsWith(".xml")))
				{
					if(sin1[1].equals(sin2[1]))	res=true;
					else				res=false;				
				}
				else
				{	try
					{
						brin3 = new BufferedReader(new FileReader("mastertemplates/"+sin1[1]));
						brin4 = new BufferedReader(new FileReader("templates/"+sin2[1]));
						while ( (((in3=brin3.readLine()) != null) && ((in4=brin4.readLine()) != null)) )
						{
							if(in3.equals(in3))	res=true;
							else			
							{
								res=false;
								break;
							}
						}
					}
					catch(Exception e)
					{
                        e.printStackTrace();
					}
					finally
					{	try
						{
							if(brin3 !=null)	brin3.close();
							if(brin4 !=null)	brin4.close();
						}
						catch(Exception e)
						{
						}
					}

				}
				if (res)	brout2.write("The test case "+ sin1[0]+ "is passed \n");
				else		brout2.write("The test case "+ sin1[0]+ "is failed \n");	

			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{	try
			{
				if(brin1 !=null)	brin1.close();
				if(brin2 !=null)	brin2.close();
				if(brout2 !=null)       brout2.close();

			}
			catch(Exception e)
			{
			}
		}
	}

}

