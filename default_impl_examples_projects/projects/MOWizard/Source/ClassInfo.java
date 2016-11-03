//$Id: ClassInfo.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.Vector;
import org.w3c.dom.*;
import java.io.*;
import javax.swing.*;
import javax.xml.parsers.*;
import com.adventnet.builder.source.sourceengine.*;
import com.adventnet.builder.source.plugin.*;
import com.adventnet.builder.xmlparser.*;
import com.adventnet.builder.framework.utils.errorhandling.*;

import com.adventnet.builder.utils.general.PureUtils;
import com.adventnet.builder.builderparser.ASTCompilationUnit;
import com.adventnet.builder.builderparser.JavaParser;
import com.adventnet.builder.builderparser.Token;
import com.adventnet.builder.builderparser.*;
import com.adventnet.builder.builderparser.visitor.*; 
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
class implementor implements StatusAndErrorInterface {
    public void showStatusMessage(String message){}
    public void showErrorMessage(String message,Exception exx){}
    public void showCriticalErrorMessage(String message,Exception ex){}
    public void showInfoMessage(String message){}
    public void showGenericMessage(String type,String message){}
}
    

public class ClassInfo implements BuilderParserInterface  {

    String configFileName,sourceFileName;
	MergeEngine mEngine=new MergeEngine();
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();    
    public ClassInfo(String configFileName,String sourceFileName) {
        this.configFileName=configFileName;
        this.sourceFileName=sourceFileName;
		mEngine.setParserInterface(this);
    }

    public String generateSource(Document doc) 
    {
        try{
            String nodename=null;
            String[] keywords= { "CLASS","MOPROPERTY" };
            String nodevalue=null;
            Vector v1=  new Vector();

            Hashtable hs=null;
            Hashtable hsforclass=new Hashtable();
            HashMap map=new HashMap(5);
            Element rootnode=doc.getDocumentElement();
            NodeList childnodes=rootnode.getChildNodes();
            NamedNodeMap attrib=null;
            org.w3c.dom.Node childname=null;
            int j=0;
			hsforclass.put("import",new ParseHolder("import","com.adventnet.nms.topodb.*,java.sql.*"));
            for(int i=0;i<childnodes.getLength();i++)
            {	
                if(!(childnodes.item(i).getNodeName().equals("#text"))){
                    childname=(Element)childnodes.item(i);
                    attrib=childname.getAttributes();
                    hs=new Hashtable();

                    for(int k=0;k<attrib.getLength();k++)
                    {
                        org.w3c.dom.Node child_attrib=attrib.item(k);
                        nodename=child_attrib.getNodeName();
                        nodevalue=child_attrib.getNodeValue();
                        if(childname.getNodeName().equals("CLASS")){
                            hsforclass.put(nodename,new ParseHolder(nodename,nodevalue));
                        }
                        if(childname.getNodeName().equals("MOPROPERTY")){
                            if(!(nodename.equals("screenname"))){
                                hs.put(nodename,new ParseHolder(nodename,nodevalue));
                            }
                        }
                    }
                    if(childname.getNodeName().equals("MOPROPERTY")){
                        v1.add(hs);
                    }
                }
            }
			Vector v2=new Vector();
			v2.add(hsforclass);
			map.put(keywords[0],v2);
            implementor imp=new implementor();
            map.put(keywords[1],v1);
            XmlDumpConfig xmlgen=new XmlDumpConfig(configFileName,map,keywords[0],false,null,imp);
            NodeList classList=doc.getElementsByTagName("CLASS");
            Element  classNode=(Element)classList.item(0);
            XMLToSourceGenerator source=new XMLToSourceGenerator(keywords,null);
            source.setSourceGeneratorListFile(getNmsHome()+"conf"+File.separator+"SourceGenerators.config");
            source.setConfigAndSourceFile(configFileName,sourceFileName);
            source.generateSource();
            return source.getGeneratedSource();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getNmsHome() {
        return "."+File.separator;
    }
	
	public void recoverableErrorsOccured(int srcType,String recoverableErrors) {
		JOptionPane.showMessageDialog(null,resourceBundle.getString("Error while parsing source file : ")+recoverableErrors,resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
	}

	public void unrecoverableErrorsOccured(int srcType,String recoverableErrors,Throwable unrecoverableError) throws Exception {
		if(srcType==TOOLSRC) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Error while parsing tool generated source file : ")+recoverableErrors,resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);	
		}
		else {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Error while parsing user writtensource file : ")+recoverableErrors,resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);	
		}
	}

	public void processErrorMsg(String errMsg) {
		JOptionPane.showMessageDialog(null,resourceBundle.getString("Error : ")+errMsg,resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);	
	}
	
	public void parsingCompleted(int srcType) {
		if(srcType == USERSRC) {
		}
	}	

	public String mergeSource(String toolGenSource,String prevSource) throws Exception {
		String newMergedSource=null;
		newMergedSource=mEngine.merge(toolGenSource,prevSource);
		return newMergedSource;
	}

	public boolean checkIfParsingNeeded() {
		return true;
	}


}	

