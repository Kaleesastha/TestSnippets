//$Id: ManagedObjectModel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import com.adventnet.nms.tools.objtorel.TransverseContainer;
import java.util.Vector;
import org.w3c.dom.Document;
import java.io.File;

public class ManagedObjectModel {
    
    // variable to keep track of the type of the project to which
    // this node is associated to .
    private int projType;
    // To find out whether this node is a leaf or not.
    boolean leaf;
    // To find find out the class Name of the ManagedObject to which 
    // this node is associated to .  
    String  classNodeName;
    // Stores the package name if this node represents a Project.
    String packageName;
    Document doc;
    //  Stores the fileName if this node represents a Source/Discovery Filter/UserTester.
    File     fileName;

    boolean classLoaded=false;

    public ManagedObjectModel() {
        
    }

    public ManagedObjectModel(int projectType,String className) {
        projType=projectType;
        leaf=true;
        classNodeName=className;
    }
    
    public ManagedObjectModel(int projectType,String className,Document doc) {
        projType=projectType;
        leaf=false;
        classNodeName=className;
    }

    public void setProjType(int projectType) {
        projType=projectType;
    }

    public int getProjType() {
        return projType;
    }

    public void setLeaf(boolean isLeaf) {
        leaf=isLeaf;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setPackageName(String packName) {
        packageName=packName;
    }

    public String getPackageName() {
        return packageName;
    }
    
    public void setClassNodeName(String className) {
        classNodeName=className;
    }

    public String getClassNodeName() {
        return classNodeName;
    }

    public void setDocInfo(Document document) {
        doc=document;
    }

    public Document getDocInfo() {
        return doc;
    }
    
    public String toString() {
        if(getProjType()==1001) {
            return "Status Poller";
        }
        else if(getProjType()==1002) {
            return "Discovery Filter";
        }
		else if(getProjType()==1003) {
			return "Relational Java";
		}
		else if(getProjType()==1004) {
			return "Deployment";
		}
        return classNodeName;
    }

     public void setClassLoaded(boolean loadClass) {
        classLoaded=loadClass;
    }

    public boolean isClassLoaded() {
        return classLoaded;
    }

    public void setFileName(File fName) {
        fileName=fName;
    }

    public File getFileName() {
        return fileName;
    }
    
}

