//$Id: FileWriter.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.utils.*;
import com.adventnet.nms.util.*;
import java.util.Vector;
import java.util.StringTokenizer;
import org.w3c.dom.*;
import com.adventnet.editor.text.*;
import org.xml.sax.*;
import com.adventnet.nms.tools.objtorel.EditableList;
import javax.xml.parsers.*;
import com.adventnet.editor.JMTextPane;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import java.util.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class FileWriter extends AbstractTransversePanel implements ActionListener,TransverseListener 
{
	private boolean initialized = false;
	JPanel  Top = null;
	JTabbedPane  optionsTabPane = null;
	JPanel  compilePanel = null;
	JLabel  spaceLabel = null;
	JPanel  statusPanel = null;
	JTextArea  textStatus= null;
	JScrollPane textStatusScroll=null;
	JPanel  erasePanel = null;
	JButton  buttonErase = null;
	JavaTextPane textSource=null; 
	JScrollPane textSourceScroll=null;
	JPanel  oKCancelPanel = null;
	JButton compileOptButton=null;

	JButton  compileButton = null;
	JButton  saveButton = null;
	JPanel   panelSaveCompile=null;
	JPanel   UserDiscPanel=null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	JButton loadButton=null;
	JFileChooser fileLoad=null;
	String tempSource="";
	JDialog dlg=null;
	String tempfilePath=null;

	//Custom variable declerations in this FileWriter Screen.  
	String DiscFilSavePath=null;
	String UserTestSavePath=null;
	JFileChooser fileSave=null;
	int filterIndex=0;
	boolean isFileSaved=false;
	boolean UserTestCompileStatus=false;
	boolean DiscFilCompileStatus=false;
	boolean defined;
	String OutputDir=null;
	private String tempPath=null;
	int compileStatus=2;
	String isLoaded=null;

	TransverseContainer transCon=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public FileWriter(){
		initCompileStat();
	}
	public void setUpPropForStat()
	{
		textStatus.setWrapStyleWord(true);
		textStatus.setLineWrap(true);
		buttonErase.setText(resourceBundle.getString("Clear Status"));
		buttonErase.setToolTipText(resourceBundle.getString("Click on Button to clear Status Screen"));
		compileButton.setText(resourceBundle.getString("Compile"));
		saveButton.setText(resourceBundle.getString("Save Source"));
		compileOptButton.setText(resourceBundle.getString("Compile Options"));
		loadButton.setText(resourceBundle.getString("Browse"));

		// Add keyboard shortcuts to buttons.  
		buttonErase.setMnemonic(KeyEvent.VK_E);
		compileButton.setMnemonic(KeyEvent.VK_C);
		saveButton.setMnemonic(KeyEvent.VK_S);
		compileOptButton.setMnemonic(KeyEvent.VK_O);
		loadButton.setMnemonic(KeyEvent.VK_B);
		loadButton.setEnabled(false);
		compileButton.setEnabled(false);

		// Add actionListeners for all these buttons.  
		buttonErase.addActionListener(this);
		compileButton.addActionListener(this);
		saveButton.addActionListener(this);		
		compileOptButton.addActionListener(this);
		loadButton.addActionListener(this);
	} 

	public void initCompileStat()
	{
		//this.setSize(getPreferredSize().width+788,getPreferredSize().height+580);
		Container container=this;
		container.setLayout(new BorderLayout());
		initVariablesForStat();
		setUpGUIForStat(container);
		setUpPropForStat();
	}	

	public void initVariablesForStat()
	{
		Top= new JPanel();
		optionsTabPane= new JTabbedPane();
		statusPanel= new JPanel();
		textStatus= new JTextArea(30,50);
		erasePanel= new JPanel();
		textSource=new JavaTextPane();
		compileButton=new JButton();
		saveButton=new JButton();
		compileOptButton=new JButton();
		panelSaveCompile=new JPanel();
		buttonErase=new JButton();
		UserDiscPanel=new JPanel();
		loadButton=new JButton();
		textStatusScroll=new JScrollPane(textStatus);
		textSourceScroll=new JScrollPane(textSource);
	} 

	public void setUpGUIForStat(Container container)
	{
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset=new Insets(0,5,0,0);	
		setConstraints(0,0,1,1,0.1,0.2,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(textSourceScroll,cons);

		inset=new Insets(0,5,0,0);
		setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(optionsTabPane,cons);
		optionsTabPane.addTab(resourceBundle.getString("Status"),null,statusPanel,null);
		statusPanel.setLayout(new BorderLayout(5,5));
		statusPanel.add(textStatusScroll,BorderLayout.CENTER);
		statusPanel.add(erasePanel,BorderLayout.SOUTH);
		erasePanel.setLayout(new FlowLayout(2,5,5));
		erasePanel.add(compileOptButton);
		erasePanel.add(saveButton);
		erasePanel.add(compileButton);
		erasePanel.add(buttonErase);
	}

	public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY ) { 
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.gridheight = height;
		cons.weightx = wtX;
		cons.weighty = wtY;
		cons.anchor = anchor;
		cons.fill = fill;
		cons.insets = inset;
		cons.ipadx = padX;
		cons.ipady = padY;
	}

	public void compileButton_Clicked(ActionEvent ae) {
		if(!saveFile()) {
			return;
		}
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		Element  options=(Element)doc.getElementsByTagName("COMPILE_OPTIONS").item(0);
		DocumentBuilderFactory dbf=null;
		DocumentBuilder db=null;
		Document fileDoc=null;
		if(options==null) {
			try {
				if(new File("."+File.separator+"projects"+File.separator+"coptions.xml").exists()) {
					// Try to getParameters from coptions.xml.
					dbf=DocumentBuilderFactory.newInstance();
					db=dbf.newDocumentBuilder();
					fileDoc=db.parse(new File("."+File.separator+"projects"+File.separator+"coptions.xml"));
					options=(Element)fileDoc.getElementsByTagName("Options").item(0);
					if(options==null) {
						COptions copt=new COptions((JDialog)getParentContainer(this),resourceBundle.getString("Compile Options"));
						copt.init();
						copt.setModal(true);
						copt.setLocation(getScrLoc(copt));
						copt.setVisible(true);
						compileButton_Clicked(null);
						return;
					}
				}
				else {
					COptions copt=new COptions((JDialog)getParentContainer(this),resourceBundle.getString("Compile Options"));
					copt.init();
					copt.setModal(true);
					copt.setLocation(getScrLoc(copt));
					copt.setVisible(true);
					compileButton_Clicked(null);
					return;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		CompileScr compile=new CompileScr();
		compile.setTextArea(textStatus);
		String projpath=(String)transCon.getTransverseComponent("currentProject");
		File f=null;
		if(!(f=new File(projpath+File.separator+"classes")).exists()){
			f.mkdir();
		}
		compile.compileClicked(getSavedPath(),options.getAttribute("JDKPath"),options.getAttribute("ClassPath"),f.toString());
		// This part of code is executed after the dialog is made invisible.  
		compileStatus=compile.getCompileStatus();

		if(filterIndex==1) {
			UserTestCompileStatus=(compileStatus==0) ?true:false;
		}
		else if(filterIndex==2) {
			DiscFilCompileStatus=(compileStatus==0) ? true : false;
		}
		try{
			if(filterIndex==1) {
				transCon.addTransverseComponent("USR_TST_CLSNAME",getClassNameFromFile());
				transCon.addTransverseComponent("USR_TST_FILENAME",getSavedPath());
				transCon.addTransverseComponent("LOAD",isLoaded);
			}
			else if (filterIndex==2) {
				transCon.addTransverseComponent("DSC_FIL_CLSNAME",getClassNameFromFile());
				transCon.addTransverseComponent("DSC_FIL_FILENAME",getSavedPath());
				transCon.addTransverseComponent("LOAD",isLoaded);
			}
		}
		catch(IOException ioe){
			ioe.getMessage();
		}
	}	

	public String getClassNameFromFile() throws IOException {
		String FilePath=getSavedPath();
		BufferedReader br;
		if(FilePath==null) {
			return null;
		}
		File f=new File(FilePath);
		if(!f.exists()) {
			return null;
		}
		try {
			br=new BufferedReader(new FileReader(FilePath));
		}catch(FileNotFoundException fnfe) {
			throw new IOException(fnfe.getMessage());
		}
		// First find the package name and then skip import statements.
		// Then find the class name.  
		String str=null; 
		String packagename=null;
		String className=f.getName().substring(0,f.getName().indexOf("."));
		while( (str=br.readLine())!=null) {
			str=str.trim();
			if(str.startsWith("package")) {
				packagename=str.substring(8,str.indexOf(";"));
				break;
			}	
		}
		return packagename+"."+className;
	}	


	public void addTransverseContainer(TransverseContainer tCon) {
		transCon=tCon;
	}
	public void loadScreenData()
	{
		String source=null;
		String fullpath=null;
		isLoaded=(String)transCon.getTransverseComponent("LOAD");
		if(isLoaded.equals("true")){
			tempfilePath=(String)transCon.getTransverseComponent("FILENAME");
			filterIndex=Integer.parseInt((String)transCon.getTransverseComponent("SERVICETYPE"));
			fullpath=(String)transCon.getTransverseComponent("FILENAME");
			source=readFromFile(fullpath);
			textSource.setText(source);
			textSource.setCaretPosition(0);
			return;
		}
		if(isLoaded.equals("false")){
			Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
			filterIndex=Integer.parseInt((String)transCon.getTransverseComponent("SERVICETYPE"));
			if(filterIndex==1){
				tempfilePath=(String)transCon.getTransverseComponent("FILENAME");
				Element usrtest=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
				if(usrtest!=null){
					fullpath=usrtest.getAttribute("fileName");
					if(fullpath!=null && new File(fullpath).exists()){
						source=readFromFile(fullpath);
						textSource.setText(source);
						textSource.setCaretPosition(0);
						return;

					}
				}
				loadFromTemplateFile(null,filterIndex);
			}
			if(filterIndex==2){
				tempfilePath=(String)transCon.getTransverseComponent("FILENAME");
				Element discfil=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
				if(discfil!=null){
					fullpath=discfil.getAttribute("fileName");
					if(fullpath!=null && new File(fullpath).exists()){
						source=readFromFile(fullpath);
						textSource.setText(source);
						textSource.setCaretPosition(0);
						return;
					}
				}
				loadFromTemplateFile(null,filterIndex);
			}
		}
	}

	public String readFromFile(String path)
	{
		String str=null;
		StringBuffer sourcebuf=new StringBuffer();
		try{
			BufferedReader bf=new BufferedReader(new FileReader(path));
			while((str=bf.readLine()) != null){
				sourcebuf.append(str);
				sourcebuf.append("\n");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return sourcebuf.toString();
	}

	private String getSavedPath()
	{		
		if(isLoaded.equals("true")){
			return tempfilePath;
		}
		return constructSourcePath(getClassName());
	}

	private String constructSourcePath(String sourcePath) {
		String classNodePath=null;
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		Element classNode=(Element)doc.getElementsByTagName("CLASS").item(0);
		classNodePath=(String)transCon.getTransverseComponent("currentProjectPath");
		File f=new File(classNodePath+classNode.getAttribute("name"));
		if(!f.exists()) {
			f.mkdir();
		}
		if(!f.exists()) {
				JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Could not create path for Saving {0}"),
                new String[]{sourcePath}),resourceBundle.getString("Fatal Error"),JOptionPane.ERROR_MESSAGE); 
			// return null if by some change the class Node did not get created.
			return null;
		}
		classNodePath=classNodePath+classNode.getAttribute("name")+File.separator+sourcePath+".java";
		return classNodePath;
	}

	private String getSavedFile() {
		if(filterIndex==1) {
			return UserTestSavePath;
		}
		else if(filterIndex==2) {
			return DiscFilSavePath;
		}
		return null;
	}

	public void loadFromTemplateFile(String templateFile,int filterIndex) {
		// We are loading a file based on the Template specified.
		// templateFile is the name of the Template File and
		// filterIndex specifies whether it is Discovery Filter.
		// If filterIndex ==  1
		// then the user wants to write UserTester Class.
		// If filterIndex == 2
		// then the user wants to write DiscoveryFilter class.
		this.filterIndex=filterIndex;
		try {
			if(templateFile==null) {
				if(filterIndex==1) {
					String fileName=System.getProperty("user.dir");
					File f=new File(fileName+File.separator+"classes"+File.separator+"com"+File.separator+"adventnet"+File.separator+"nms"+File.separator+"tools"+File.separator+"utils"+File.separator+"TemplUST.tpl");
					char ch[]=new char[(int)f.length()];
					FileReader fRead=new FileReader(f);
					fRead.read(ch,0,(int)f.length());
					tempSource=convertFromTemplate(new String(ch),filterIndex);
					textSource.setText(tempSource);
					textSource.setCaretPosition(0);
				}
				if(filterIndex==2) {
					String fileName=System.getProperty("user.dir");
					File f=new File(fileName+File.separator+"classes"+File.separator+"com"+File.separator+"adventnet"+File.separator+"nms"+File.separator+"tools"+File.separator+"utils"+File.separator+"TemplDsc.tpl");
					char ch[]=new char[(int)f.length()];
					FileReader fRead=new FileReader(f);
					fRead.read(ch,0,(int)f.length());
					tempSource=convertFromTemplate(new String(ch),filterIndex);
					textSource.setText(tempSource);
					textSource.setCaretPosition(0);
				}
			}
		}
		catch(IOException ioe) {
			System.out.println(resourceBundle.getString(" Error occurred while reading UserTester Template FIle"));
		}
	}

	public Container getParentContainer(Container con){
		while(!(con instanceof JDialog)){
			con=con.getParent();
		}
		return con;
	}

	public int nextActionPerformed(String str){
		return 1;
	}
	public int previousActionPerformed(String str){
		return 1;
	}
	public boolean finishActionPerformed()
	{
		if(compileStatus!=0){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please compile the source to effect the changes"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
			return false;
		}
		if(filterIndex==1) {
			UserTestCompileStatus=(compileStatus==0) ?true:false;
		}
		else if(filterIndex==2) {
			DiscFilCompileStatus=(compileStatus==0) ? true : false;
		}
		try{
			if(filterIndex==1) {
				transCon.addTransverseComponent("USR_TST_CLSNAME",getClassNameFromFile());
				transCon.addTransverseComponent("USR_TST_FILENAME",getSavedPath());
				transCon.addTransverseComponent("USR_LOAD",isLoaded);

			}
			else if (filterIndex==2) {
				transCon.addTransverseComponent("DSC_FIL_CLSNAME",getClassNameFromFile());
				transCon.addTransverseComponent("DSC_FIL_FILENAME",getSavedPath());
				transCon.addTransverseComponent("DSC_LOAD",isLoaded);
			}
		}
		catch(IOException ioe){
			ioe.getMessage();
		}
		((JDialog)getParentContainer(this)).dispose();
		return true;
	}
	public void cancelActionPerformed(String str)
	{
		if(filterIndex==1) {
			transCon.removeTransverseComponent("USR_TST_CLSNAME");	
		}
		else {
			transCon.removeTransverseComponent("DSC_FIL_CLSNAME");
		}
		((JDialog)getParentContainer(this)).dispose();
	}
	public void closeActionPerformed(){}
	public String getPackage()
	{
		if(tempfilePath.indexOf('.')==-1){
			return null;
		}
		return tempfilePath.substring(0,tempfilePath.lastIndexOf('.'));
	}

	public String getClassName()
	{
		if(tempfilePath.lastIndexOf('.')==-1){
			return tempfilePath;
		}
		return tempfilePath.substring(tempfilePath.lastIndexOf('.')+1);
	}

	private String convertFromTemplate(String templateFile,int filterIndex) {
		StringBuffer strbuf=new StringBuffer(templateFile);
		int UserTestCount=0,DiscFilterCount=0;
		String fileName=null;
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		Element node=(Element)doc.getElementsByTagName("CLASS").item(0);
		switch(filterIndex) {
			case 1:
				/* 
				   Do the same as given in case 2;
				 */
				if(transCon.containsKey("USERTEST_COUNT")==false) {
					transCon.addTransverseComponent("USERTEST_COUNT",new Integer(1));
				}
				else {
					UserTestCount=((Integer)transCon.getTransverseComponent("USERTEST_COUNT")).intValue();
				}
				fileName="UserTester"+(UserTestCount+1);
				while(templateFile.indexOf("<#*CLASSNAME*#>")!=-1) {
					int index=templateFile.indexOf("<#*CLASSNAME*#>");
					strbuf.replace(index,index+15,getClassName());
					templateFile=strbuf.toString();
				}
				while(templateFile.indexOf("<#*PACKAGE*#>")!=-1){
					int index=templateFile.indexOf("<#*PACKAGE*#>");	
					if(getPackage()==null){
						strbuf.replace(index,index+13,node.getAttribute("package"));
						templateFile=strbuf.toString();
						break;
					}
					strbuf.replace(index,index+13,getPackage());
					templateFile=strbuf.toString();
				}
				break;
			case 2:
				/*
				   Retrieve from TransverseContainer the no of instances of UserTester 
				   that are written by the user.  If the user has already one change the
				   class name accordingly, by replacing <#*CLASSNAME*#> tag by the actual
				   class name.  
				 */     
				if(transCon.containsKey("DISC_FILTER_COUNT")==false) {
					transCon.addTransverseComponent("DISC_FILTER_COUNT",new Integer(1));
				}	
				else {
					DiscFilterCount=( (Integer)transCon.getTransverseComponent("DISC_FILTER_COUNT")).intValue();
				}	
				fileName="DiscoveryFilter"+(DiscFilterCount+1);
				while(templateFile.indexOf("<#*CLASSNAME*#>")!=-1) {
					int index=templateFile.indexOf("<#*CLASSNAME*#>");
					strbuf.replace(index,index+15,getClassName());
					templateFile=strbuf.toString();
				}
				while(templateFile.indexOf("<#*PACKAGE*#>")!=-1){
					int index=templateFile.indexOf("<#*PACKAGE*#>");	
					if(getPackage()==null){
						strbuf.replace(index,index+13,node.getAttribute("package"));
						templateFile=strbuf.toString();
						break;
					}
					strbuf.replace(index,index+13,getPackage());
					templateFile=strbuf.toString();
				}

				break;
		}
		// Now the templateFile has changed and code fragments are replaced.  
		return templateFile;
	}

	private void saveButton_Clicked(ActionEvent ae) {
		if(textSource.getText().trim().length()==0){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please Load/Define the class and then save it!!!"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
			return;
		}
		String path=getSavedPath();
		if(path==null) {
			return;
		}
		saveFile();
		JOptionPane.showMessageDialog(null,resourceBundle.getString("Saved File : ")+new File(path).getName());
		compileButton.setEnabled(true);
	}

	private boolean saveFile() {
		try {
			if(getSavedPath()==null) {
				return false;
			}
			FileOutputStream fout=new FileOutputStream(getSavedPath());
			fout.write(textSource.getText().trim().getBytes());
			fout.close();
		}
		catch(IOException ioe) {
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error occurred while writing to file {0}"),
                new String[]{getSavedPath()}),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE); 
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == compileButton ) {
			compileButton_Clicked(ae);
		}
		else if(ae.getSource() == saveButton ) {
			saveButton_Clicked(ae);
		}
		else if(ae.getSource() == buttonErase) {
			textStatus.setText(" ");
		}
		else if(ae.getSource() == compileOptButton){
			compileOptButton_Clicked(ae);
		}
	}

	public Point getScrLoc(Component comp) {
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}

	public void compileOptButton_Clicked(ActionEvent ae){
		COptions copt=new COptions((JDialog)getParentContainer(this),resourceBundle.getString("Compile Options"));
		copt.init();
		if(copt.readXML()){
			copt.initialize();
		}
		copt.setTitle(resourceBundle.getString("Compile Options"));
		copt.setLocation(getScrLoc(copt));		
		copt.setVisible(true);
	}
}
