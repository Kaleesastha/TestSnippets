//$Id: RebrandingProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.studio.tools.rebranding;
import com.adventnet.nms.tools.utils.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Enumeration;
import com.adventnet.nms.tools.utils.FileUtil;

public class RebrandingProperties{

	private String language=null; 
	private String country=null;
	private Element root=null; // Root element of the RebrandingProperties.xml
           private File sourceFile=null;
            private File destFile=null;

	public RebrandingProperties(String propsXML){
		Document doc=XmlUtil.parseAndCreateDocument(propsXML);
		if(doc!=null){
			root=doc.getDocumentElement();
			language=root.getAttribute("language");
			country=root.getAttribute("country");
		}

	}
	public RebrandingProperties(Document doc){
		if(doc!=null){
			root=doc.getDocumentElement();
			language=root.getAttribute("language");
			country=root.getAttribute("country");
		}
	}
	public boolean rebrand(String backupName){
		backupName=replaceBackSlash(backupName+"/html");
		File backupDir=new File(backupName);
		if(!backupDir.exists()){
			backupDir.mkdirs();
		}
		NodeList fileList=root.getElementsByTagName("file");
		boolean result=true;
		for(int i=0;i<fileList.getLength();i++){
			if(fileList.item(i).getNodeType()==Node.ELEMENT_NODE){
				//take each properties file present in the XML
				Element file=(Element)fileList.item(i);
				NodeList paramList=file.getElementsByTagName("parameter");
				if(paramList.getLength()==0){
					// If there are no parameters there is no need to update this file
					continue;
				}
				if(!updatePropertiesFile(file,backupDir)){
					result=false;
				}

			}
		}
		return result;

	}

	private boolean updatePropertiesFile(Element propsFile,File backupDir){

		// Check whether the source and desination exists
		String source=replaceBackSlash(propsFile.getAttribute("source"));
		sourceFile=new File(source+"_"+language+"_"+country+".properties");
		if(!sourceFile.exists()){
			sourceFile=new File(source+".properties");
		}
		if(!sourceFile.exists()){
			System.out.println("Properties file "+sourceFile.toString()+" doesn't exist ");
			return false;
		}
		String dest=replaceBackSlash(propsFile.getAttribute("destination"));
		destFile=new File(dest+"_"+language+"_"+country+".properties");
		if(!destFile.exists()){
			destFile=new File(dest+".properties");
		}
		if(!destFile.exists()){
			System.out.println("Properties file "+destFile.toString()+" doesn't exist ");
			return false;
		}
		FileInputStream sourceFileIn=null;
		FileInputStream destFileIn=null;
		FileOutputStream fout=null;
		try{
			// Load the source and destination properties file.
			Properties sourceProp=new Properties();
			sourceFileIn=new FileInputStream(sourceFile);
			sourceProp.load(sourceFileIn);
			Properties destProp=new Properties();
			destFileIn=new FileInputStream(destFile);
			destProp.load(destFileIn);
			File backupFile=new File(backupDir,destFile.getName());						    if(!backupFile.exists()){
				FileUtil.copyFile(destFile,backupFile);
			}
			 

			//Update the properties file
			updateProperties(sourceProp,destProp,propsFile);

			//Write the modified properties to disk.
			fout=new FileOutputStream(destFile);
			System.out.println(" File "+destFile+" updated");

			destProp.store(fout,"");
		}
		catch(Exception e){
			System.out.println("Exception while parsing properties "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally{
			try{
				if(sourceFileIn!=null){
					sourceFileIn.close();
				}
				if(destFileIn!=null){
					destFileIn.close();
				}
				if(fout!=null){
					fout.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return true;
	}


	//Source and Destination properties are passed along with the parameters to be changed
	//This method will make necessary changes in the destination properties

	private void updateProperties(Properties sourceProp, Properties destProp, Element propsFile){
		NodeList paramList=propsFile.getElementsByTagName("parameter");
		for(int j=0;j<paramList.getLength();j++){
			if(paramList.item(j).getNodeType()==Node.ELEMENT_NODE){
				Element parameter=(Element)paramList.item(j);
				String key=parameter.getAttribute("key");
				String oldValue=parameter.getAttribute("oldValue");
				String newValue=parameter.getAttribute("newValue");
				
				// For these two keys the values will be somewhat different
				// like WebNMS=Web NMS. This is not captured in the RebrandingProperties.conf
				// Hence this "Web NMS" is changed to "WebNMS" then properties updation is done.
				String sourcePropFile=propsFile.getAttribute("source");
				if(key.equals("WebNMS")&&sourcePropFile.endsWith("EnglishToNativeComplete")){
					destProp.put("<PROMINENT_KEY>WebNMS",oldValue);
					destProp.put("<PROMINENT_KEY>WebNMS Version x.x",oldValue+" Version x.x");
				}	
				//This check is essential otherwise there will be infinite looping
				// This is because if the oldvalue is "AdventNet" and the newValue
				//is "AdventNet Test" there is no need to replace anything.
				/*if(newValue.indexOf(oldValue)!=-1){
					continue;
				}*/
				//Get all the properties from the file
				//and check whether it needs to be changed.
				for(Enumeration e=sourceProp.propertyNames();e.hasMoreElements();){
					String prop=e.nextElement().toString();
					String currentValue=null;
					if(prop.indexOf(key)!=-1){
						//The value corresponding to this key needs to be changed
						
						// There is a possibility that this key is present in the
						// destination file already.
						if(destProp.containsKey(prop))
						{
                                // commented for fixing the bug of  strings like
                                // "AdventNet WebNMS" not getting rebranded
                                //if(!sourceFile.getName().equalsIgnoreCase(destFile.getName()))
                                //               {
                                //           continue;
                                //      }
							currentValue=destProp.getProperty(prop);
						}
						else{
							currentValue=sourceProp.getProperty(prop);
						}
						if(currentValue==null || currentValue.trim().equals("")){
							currentValue=prop;
							//There is a possibility that the "<PROMINENT_KEY>"
							//will be there in key
							if(currentValue.startsWith("<PROMINENT_KEY>")){
								currentValue=currentValue.substring
									("<PROMINENT_KEY>".length());
							}
						}
						int index=currentValue.indexOf(oldValue);
						// There can be possibility for more than one value change
						//in a particular property
						int newValueIndex=newValue.indexOf(oldValue);
						
						while(index!=-1){
							currentValue=currentValue.substring(0,index)+newValue
								+currentValue.substring(index+oldValue.length());
								
								     	index=currentValue.indexOf(oldValue,newValueIndex+index+1);

								        	}
							

						
						
						
						destProp.setProperty(prop,currentValue);

					}

				}

			}
		}
	}


	private String replaceBackSlash(String file){
		file=file.replace('\\',File.separatorChar);
		file=file.replace('/',File.separatorChar);
		return file;
	}
	public static void main(String args[]){
		RebrandingProperties rebrandingProps=new RebrandingProperties(args[0]);
		boolean  result=rebrandingProps.rebrand(args[0]);        
		System.out.println("Rebranding Result "+result);
	}

}




