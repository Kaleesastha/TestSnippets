
//$Id: ApplyChanges.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.confchanges;
import java.io.*;
import java.util.*;
import com.adventnet.nms.tools.nar.NarUtils;
import com.adventnet.nms.util.*;
public class ApplyChanges
{
	String nmsHome=null;
	String version=null;
	Vector filesPresent=null;
	Vector directoryPresent=null;
	XMLDataReader reader=null;
	XMLDataWriter writer=null;
	public ApplyChanges(String home)
	{
		nmsHome=home;
	}
	public void update(Vector childNodes) throws ClassNotFoundException,InstantiationException,IllegalAccessException 
	{
		checkForVersion();
		for(int i=0;i<childNodes.size();i++)
		{
			XMLNode node = (XMLNode)childNodes.elementAt(i);
			if(!(node.getNodeType() == XMLNode.ELEMENT))
			{
				continue;					
			}								
			String className = (String)node.getAttribute("classname");
			String fileName = (String)node.getAttribute("name");
			Class cl = Class.forName(className);
			Class iface = Class.forName("com.adventnet.nms.tools.confchanges.ConfChanges");
			if(iface.isAssignableFrom(cl))
			{	
				com.adventnet.nms.tools.confchanges.ConfChanges obj = (com.adventnet.nms.tools.confchanges.ConfChanges)cl.newInstance();

				//obj.updateChanges(node,nmsHome);
				File backupDir=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+version);
				if(!backupDir.isDirectory())
				{
					backupDir.mkdirs();
				}
				if(obj.updateChanges(node,nmsHome,backupDir))
				{
					System.out.println(	" Configuration Updated in file " + fileName );
				}
				else
				{
					System.out.println(	" Configuration  Not Updated in file " + fileName );
				}
			}
		}
		//if the patch is not updated earlier then we have to write it in xml other wisenot needed
		if(!checkWhetherPatchIsUpdated())
		{
			writeInstalledPatchesXML(getCurrentVersionFromPatchInfo(),version);
		}
		
	}
	public void revert(String ver) throws IOException
	{
		
		Vector v=getVersionsInstalledAfterThis(ver);

		if(v.size()==0)
		{
			System.out.println("No backup Information available for version"+ver);
			return;
			
		}
		//revertingshould be done from bootom to top
		for(int i=v.size()-1;i>=0;i--)
		{
			String version=v.elementAt(i).toString();
			File bkpDir = new File(nmsHome + File.separator + "conf" + File.separator + "backup" + File.separator +version);
			if(!bkpDir.exists())
			{
				System.out.println(	" No backup Information available for version " +version);
				return;
			}
			filesPresent=new Vector();
			directoryPresent=new Vector();
			getFilesAndDirectories(bkpDir);
			File confFile = new File(nmsHome);
			copyFromBackUp(confFile.toString(),version);
			bkpDir.delete();
		}
		writeInstalledPatchesXML(ver,"");
	}
	public void checkForVersion() 	
	{
		File f1=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+"BaseVersion");
			filesPresent=new Vector();
			directoryPresent=new Vector();
		if(!f1.isDirectory())
		{
			f1.mkdirs();
			
			File f2=new File(nmsHome+File.separator+"conf"+File.separator+"backup");
			getFilesAndDirectories(f2);
			if(filesPresent.size()>0)
			{
				copyFromBackUp(f1.toString(),"backup");
				version="ServicePack_1";
				writeInstalledPatchesXML("ServicePack_1","BaseVersion");
			}
			else
			{
				version="BaseVersion";
				writeInstalledPatchesXML("BaseVersion","");
				//even after revert also the directories are not getting deleted
				deleteDirectories();
			}

		}
		else
		{
			getFilesAndDirectories(f1);
			//there may be a directory with out any files in that case also the version is base version
			if(filesPresent.size()>0)
			{
				if(!checkWhetherPatchIsUpdated())
				{
					//if this patch is not updated then backup directory is created with the current version name
					version=getVersionFromInstalledPatchesXML("currentversion");
				}
				else
				{
					//otherwise it is created with  the previous version name
					String installedVersions=getVersionFromInstalledPatchesXML("installedversions");
					version=installedVersions.substring(installedVersions.lastIndexOf(",")+1);

					
				}
			}
			else
			{
				version="BaseVersion";
				writeInstalledPatchesXML("BaseVersion","");
			}
		}

	}
	private boolean checkWhetherPatchIsUpdated()
	{
		String currentVersionOfTool=getVersionFromInstalledPatchesXML("currentversion");
		String currentVersionFromPatchInfo=getCurrentVersionFromPatchInfo();
		if(currentVersionOfTool.trim().equals(currentVersionFromPatchInfo.trim()))
		{
			return true;
		}
		return false;
	}
	private void copyFromBackUp(String underWhichDir,String fromWhichDir)
	{
		for(int i=0;i<filesPresent.size();i++)
		{
			File oldFile=(File)filesPresent.elementAt(i);
			String path=oldFile.getPath();
			String file=path.substring(path.lastIndexOf(File.separator)+1);
			String s1=path.substring(path.indexOf(fromWhichDir)+fromWhichDir.length(),path.lastIndexOf(File.separator));
			File newDir=new File(underWhichDir+s1);
			if(!newDir.isDirectory())
			{
				newDir.mkdirs();
			}
			File newFile=new File(newDir.toString()+File.separator+file);
			NarUtils.copyFile(oldFile,newFile);
			System.out.println(newFile.toString()+ " Restored from Backup ");
			oldFile.delete();
		}
		deleteDirectories();

	}
	private void getFilesAndDirectories(File f)
	{
		File[] file=f.listFiles();
		for(int i=0;i<file.length;i++)
		{
			
			if(file[i].isFile())
			{
				filesPresent.add(file[i]);
			}
			else if(file[i].isDirectory())
			{
				//patch Info directory will be created which will contain the PatchInfo.xml it should be taken while calculating the files.
				if(!file[i].getName().equals("PatchInfo"))
				{
					directoryPresent.add(file[i]);
					getFilesAndDirectories(file[i]);
				}
			}
		}
	}
	private void deleteDirectories()
	{
		//for deleteing a directory its inner directories has to be deleted first.
		for(int i=directoryPresent.size()-1;i>=0;i--)
		{
			File dir=(File)directoryPresent.elementAt(i);
			if(!dir.getPath().endsWith("BaseVersion"))
			{
				dir.delete();
			}
		}
	}	
	private Vector getVersionsInstalledAfterThis(String ver)
	{
		Vector versionsList=new Vector();
		File f=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+"PatchInfo"+File.separator+"InstalledPatches.xml");
		if(f.exists())
		{
			reader=new XMLDataReader(f.toString(),false);
			XMLNode root=reader.getRootNode();
			Vector v=root.getChildNodes();
			for(int i=0;i<v.size();i++)
			{
				XMLNode node=(XMLNode)v.elementAt(i);
				if(node.getAttribute("installedversions")!=null)
				{
					String versions=node.getAttribute("installedversions").toString();
					if(versions.trim().equals(""))
					{
						return versionsList;
					}
					else
					{
						int index=versions.indexOf(ver);
						if(index!=-1)
						{
							String reqVersions=versions.substring(index);
							StringTokenizer tok=new StringTokenizer(reqVersions,",");
							while(tok.hasMoreTokens())
							{
								versionsList.add(tok.nextToken());
							}
							//we have to remove all these versions from this file
							if(index==0)
							{
									node.setAttribute("installedversions","");				
							}
							else
							{
								node.setAttribute("installedversions",versions.substring(0,index-1));				
							}
						}
						
					}
				}
			}
				writer=new XMLDataWriter(f.toString(),root);
		}

		return versionsList;	
	}
	private void writeInstalledPatchesXML(String currentversion,String previuosversion)
	{
		File f=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+"PatchInfo"+File.separator+"InstalledPatches.xml");
		if(f.exists())
		{
			reader=new XMLDataReader(f.toString(),false);
			XMLNode root=reader.getRootNode();
			Vector v=root.getChildNodes();
			for(int i=0;i<v.size();i++)
			{
				XMLNode node=(XMLNode)v.elementAt(i);
				if(node.getAttribute("currentversion")!=null)
				{
					node.setAttribute("currentversion",currentversion);
				}
				else if(node.getAttribute("installedversions")!=null)
				{
					String versions=node.getAttribute("installedversions").toString();
					if(versions.trim().equals(""))
					{
						versions=previuosversion;
					}
					else
					{
						if(!previuosversion.trim().equals(""))
						{
							versions=versions+","+previuosversion;
						}
					}
					node.setAttribute("installedversions",versions);
				}
			}
			writer=new XMLDataWriter(f.toString(),root);
		}
		else
		{
				XMLNode root=new XMLNode();
				root.setNodeName("INSTALLED_PATCHES");
				root.setNodeType(XMLNode.ELEMENT);
				XMLNode node1=new XMLNode();
				node1.setNodeName("property");
				node1.setNodeType(XMLNode.ELEMENT);
				node1.setAttribute("currentversion",currentversion);
				root.addChildNode(node1);
				XMLNode node2=new XMLNode();
				node2.setNodeName("property");
				node2.setNodeType(XMLNode.ELEMENT);
				node2.setAttribute("installedversions",previuosversion);
				root.addChildNode(node2);
				writer=new XMLDataWriter(f.toString(),root);
		}
	}
	public String getVersionFromInstalledPatchesXML(String s)
	{
		File f=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+"PatchInfo"+File.separator+"InstalledPatches.xml");
		if(f.exists())
		{
			reader=new XMLDataReader(f.toString(),false);
			XMLNode root=reader.getRootNode();
			Vector v=root.getChildNodes();
			for(int i=0;i<v.size();i++)
			{
				XMLNode node=(XMLNode)v.elementAt(i);
				if(node.getAttribute(s)!=null)
				{
					return node.getAttribute(s).toString();
				}
			}
		}
		return "";
	}
	private String getCurrentVersionFromPatchInfo()
	{
		File f=new File(nmsHome+File.separator+"conf"+File.separator+"backup"+File.separator+"PatchInfo"+File.separator+"PatchInfo.xml");
		if(f.exists())
		{
			reader=new XMLDataReader(f.toString(),false);
			XMLNode root=reader.getRootNode();
			Vector v=root.getChildNodes();
			for(int i=0;i<v.size();i++)
			{
				XMLNode node=(XMLNode)v.elementAt(i);
				if(node.getAttribute("name")!=null&&node.getAttribute("name").toString().equals("PatchVersion"))
				{
					return node.getAttribute("value").toString();
				}
			}
		}
		return "";
	}
}
