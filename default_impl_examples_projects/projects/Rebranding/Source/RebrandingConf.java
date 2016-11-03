// $Id: RebrandingConf.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.studio.tools.rebranding;
import org.w3c.dom.Document;
import java.io.File;
import com.adventnet.nms.tools.utils.XmlUtil;
import com.adventnet.nms.tools.confchanges.ConfUpgradeManager;
import java.util.Date;
import java.text.SimpleDateFormat;
public class RebrandingConf
{
	private Document rebrandingDoc=null;
	private String productHome=null;
	private String confHome=null;
	public RebrandingConf(String productHome,String confHome){
		this.productHome=productHome;
		this.confHome=confHome;
		rebrandingDoc=XmlUtil.parseAndCreateDocument(productHome+"/conf/RebrandingConf.xml");
	}
	public RebrandingConf(Document rebrandingDoc,String productHome,String confHome){
		this.productHome=productHome;
		this.confHome=confHome;
		this.rebrandingDoc=rebrandingDoc;
	}
	public boolean rebrand(String backupName){
		if(rebrandingDoc==null){
			System.out.println("Unable to parse"+ productHome+"/conf/RebrandingConf.xml");
			return false;
		}
		ConfUpgradeManager cum=new ConfUpgradeManager(rebrandingDoc);
		cum.setProductHome(productHome);
		cum.setProductConfHome(confHome);
		cum.setBackUpDirectory(backupName);
		return cum.updateConfChanges();
	}

	public static void main(String args[]){
		RebrandingConf rebrand=new RebrandingConf(args[0],args[1]);
		System.out.println("Conf File Rebranding result "+rebrand.rebrand(args[0]));
	}	
}



