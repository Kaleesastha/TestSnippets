//$Id: NarInstUnInstInterfaceImpl.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import com.adventnet.nms.tools.nar.NarInstUnInstInterface;
import java.awt.*;
import java.io.File;
public class NarInstUnInstInterfaceImpl implements NarInstUnInstInterface {

    private String narName=null;
	 public boolean install(String archiveName) {
	     narName=archiveName.substring(archiveName.lastIndexOf(File.separator) + 1,archiveName.lastIndexOf("."));				 
		 MONarInstaller mni=new MONarInstaller();
		 mni.setNarFile(archiveName);
		 mni.disAssembleNar();
		 mni.findDependentArchives();
		 mni.prepareForUpdation();

		 JDialog dlg=new JDialog();
		 dlg.getContentPane().add(mni,BorderLayout.CENTER);
		 dlg.setSize(800,600);
		 dlg.setModal(true);
		 dlg.setVisible(true);
		 return mni.getUpdateStatus();
	 }

	 public boolean unInstall(String fileName) {
	     narName=fileName;
		 UninstallManagedObject umo=new UninstallManagedObject();
		 return umo.unInstall(fileName);
	 }

    public String[] getInstalledNarsList(){
	return new String[]{narName};
    }
    
    public String[] getUnInstalledNarsList(){
	return new String[]{narName};
    }
}
