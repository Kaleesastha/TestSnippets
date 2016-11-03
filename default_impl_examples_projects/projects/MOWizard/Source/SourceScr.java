//$Id: SourceScr.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.*;
import com.adventnet.nms.tools.objtorel.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.adventnet.editor.text.JavaTextPane;
import com.adventnet.editor.JMTextPane;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class SourceScr extends AbstractTransversePanel implements ActionListener,TransverseListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    javax.swing.JPanel  Top = null;
    javax.swing.JPanel  compilePanel = null;
    javax.swing.JButton  optButton = null;
    javax.swing.JButton  saveButton = null;
    javax.swing.JButton  compileButton = null;
    javax.swing.JButton  clearButton = null;
    javax.swing.JPanel  sourcePanel = null;
    javax.swing.JScrollPane  errorScroll = null;
    javax.swing.JTabbedPane  errorPane = null;
    javax.swing.JTextArea  errorArea = null;
    javax.swing.JScrollPane  sourceScroll = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    TransverseContainer tCon=null;
    String FS=File.separator;
    //Assigned some random value
    int compileStatus=10;
    MOEditor editor=null;
    String classpath=null;
    WriteToXML xmlwrite=null;
    JavaTextPane JavaSource=null;
    String packagename=null;
    String classname=null;
    String SourcePath=null;
    boolean firstTime=true;
    boolean isLoaded=false;
    //byte[] tempsource=null;
    //byte[] tempclass=null;
    String PS=System.getProperty("path.separator");
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
    public SourceScr()
    {
	//<Begin_SourceScr>
	this.init();

	//<End_SourceScr>
    }

    public SourceScr(boolean status)
    {
	firstTime=status;
	this.init();
    }

    public SourceScr(java.applet.Applet applet)
    {
	//<Begin_SourceScr_java.applet.Applet>
	this.applet = applet;
	this.init();

	//<End_SourceScr_java.applet.Applet>
    }

    public void start()
    { 
	//<Begin_start>

	//<End_start>
    } 
    public void initVariables()throws Exception
    { 
	JavaSource=new JavaTextPane();
	//<Begin_initVariables>
	Top= new javax.swing.JPanel();
	compilePanel= new javax.swing.JPanel();
	optButton= new javax.swing.JButton();
	saveButton= new javax.swing.JButton();
	compileButton= new javax.swing.JButton();
	clearButton= new javax.swing.JButton();
	sourcePanel= new javax.swing.JPanel();
	errorScroll= new javax.swing.JScrollPane();
	errorPane= new javax.swing.JTabbedPane();
	errorArea= new javax.swing.JTextArea();
	sourceScroll= new javax.swing.JScrollPane(JavaSource);

	//<End_initVariables>
    } 
    public void setUpToolBar()
    { 
	//<Begin_setUpToolBar>

	//<End_setUpToolBar>
    } 
    public void setUpGUI(Container container)throws Exception
    { 
	//<Begin_setUpGUI_Container>
	container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(compilePanel,BorderLayout.SOUTH);
	compilePanel.setLayout(new FlowLayout(2,5,5));
	compilePanel.add(optButton);
	compilePanel.add(saveButton);
	compilePanel.add(compileButton);
	compilePanel.add(clearButton);
	Top.add(sourcePanel,BorderLayout.CENTER);
	sourcePanel.setLayout(new GridBagLayout());
	inset = new Insets(5,5,5,0);
	setConstraints(0,1,1,1,0.1,0.3,cons.CENTER,cons.BOTH,inset,0,0);
	sourcePanel.add(errorScroll,cons);
	errorScroll.getViewport().add(errorPane);
	errorPane.addTab(resourceBundle.getString("Status"),null,errorArea,null);
	inset = new Insets(5,5,5,5);
	setConstraints(0,0,1,1,0.1,0.7,cons.CENTER,cons.BOTH,inset,0,0);
	sourcePanel.add(sourceScroll,cons);

	//<End_setUpGUI_Container>
    } 
    public void setUpProperties()throws Exception
    { 
	//<Begin_setUpProperties>

	try
	    {
		optButton.setText(resourceBundle.getString("Compile Options"));
	    }
	catch(Exception ex)
	    {
		showStatus(resourceBundle.getString("Exception while setting properties for bean ")+optButton,ex); 
	    }

	try
	    {
		saveButton.setText(resourceBundle.getString("Save Source"));
	    }
	catch(Exception ex)
	    {
		showStatus(resourceBundle.getString("Exception while setting properties for bean ")+saveButton,ex); 
	    }

	try
	    {
		compileButton.setText(resourceBundle.getString("Compile"));
	    }
	catch(Exception ex)
	    {
		showStatus(resourceBundle.getString("Exception while setting properties for bean ")+compileButton,ex); 
	    }

	try
	    {
		clearButton.setText(resourceBundle.getString("Clear Status"));
	    }
	catch(Exception ex)
	    {
		showStatus(resourceBundle.getString("Exception while setting properties for bean ")+clearButton,ex); 
	    }

	try
	    {
		errorArea.setText("");
	    }
	catch(Exception ex)
	    {
		showStatus(resourceBundle.getString("Exception while setting properties for bean ")+errorArea,ex); 
	    }

	//<End_setUpProperties>
	compileButton.addActionListener(this);
	saveButton.addActionListener(this);
	optButton.addActionListener(this);
	clearButton.addActionListener(this);
	compileButton.setEnabled(false);
	errorArea.setEditable(false);
	xmlwrite=new WriteToXML();
    } 
    public void setUpConnections()throws Exception
    { 
	//<Begin_setUpConnections>

	//<End_setUpConnections>
    } 
    public void stop()
    { 
	//<Begin_stop>

	//<End_stop>
    } 
    public void setUpMenus()
    { 
	//<Begin_setUpMenus>

	//<End_setUpMenus>
    } 
    public String getParameter(String input)
    { 
	//<Begin_getParameter_String>
	String value = null;
	if ( applet != null)
	    {    
		value = applet.getParameter(input);
	    }    
	else
	    {    
		value = (String)com.adventnet.apiutils.Utility.getParameter(input);
	    }    
	if(value == null)
	    {
		if (input.equals("HOST")) value = "localhost"; 
		if (input.equals("PORT")) value = "161"; 
	    }
	return value;

	//<End_getParameter_String>
    } 
    public void init()
    { 
	//<Begin_init>
	if (initialized == true) return; 
	//setPreferredSize(new Dimension(getPreferredSize().width+686,getPreferredSize().height+474)); 
	//setSize(getPreferredSize()); 
	Container container = this;
	container.setLayout(new BorderLayout()); 
	try 
	    { 
		initVariables(); 
		setUpGUI(container); 
		setUpProperties(); 
		setUpConnections(); 
	    } 
	catch(Exception ex) 
	    { 
		showStatus(resourceBundle.getString("Error in init method"),ex); 
	    } 
	// let us set the initialzed variable to true so  
	// we dont initialize again even if init is called 
	initialized = true; 
	setUpMenus();
	setUpToolBar();
	//<End_init>
    } 
    public void showStatus(String message)
    {
	//<Begin_showStatus_String>
	System.out.println(resourceBundle.getString("Internal Error :")+ message);
	//<End_showStatus_String>
    }
    public void showStatus(String message,Exception ex)
    {
	//<Begin_showStatus_String_Exception>
	System.out.println(resourceBundle.getString("Internal Error :")+ message);
	ex.printStackTrace();
	//<End_showStatus_String_Exception>
    }
    public void actionPerformed(ActionEvent ae)
    {
	if(ae.getSource()==compileButton){
	    compileButton_Clicked(ae);
	}
	else if(ae.getSource()==optButton){
	    optButton_Clicked(ae);
	}
	else if(ae.getSource()==saveButton){
	    saveButton_Clicked(ae);
	}
	else if(ae.getSource()==clearButton){
	    clearButton_Clicked(ae);
	}
    }

    public void addTransverseContainer(TransverseContainer transCon)
    {
	tCon=transCon;
    }

    public void compileButton_Clicked(ActionEvent ae)
    {
	Document doc=(Document)tCon.getTransverseComponent("XMLMODEL");
	Element  options=(Element)doc.getElementsByTagName("COMPILE_OPTIONS").item(0);
	DocumentBuilderFactory dbf=null;
	DocumentBuilder db=null;
	Document fileDoc=null;
	File f=new File("."+FS+"projects"+File.separator+"coptions.xml");
	if(options==null) {
	    try {
		if(f.exists()) {
		    dbf=DocumentBuilderFactory.newInstance();
		    db=dbf.newDocumentBuilder();
		    fileDoc=db.parse(f);
		    options=(Element)fileDoc.getElementsByTagName("Options").item(0);
		    if(options==null) {
			callCompileOptions();
			compileButton_Clicked(null);
			return;
		    }
		}
		else {
		    callCompileOptions();
		    compileButton_Clicked(null);
		    return;
		}
	    }
	    catch(Exception e) {
		e.printStackTrace();
	    }
	}
	CompileScr compile=new CompileScr();
	compile.setTextArea(errorArea);
	classpath=options.getAttribute("ClassPath");
	classpath=classpath.trim();
	String clspath=PS+"."+FS+"classes";
	if(classpath.indexOf(clspath)!=-1){
	    //remove the .classes as it wud be added again in compilescr
	    classpath=classpath.substring(0,classpath.indexOf(clspath))+classpath.substring(classpath.indexOf(clspath)+clspath.length(),classpath.length());
	}
	String projpath=(String)tCon.getTransverseComponent("currentProject")+"classes";
	if(!(f=new File(projpath)).exists()){
	    f.mkdir();
	}
	String moclasspath=getClasspath();
	if(moclasspath.trim().startsWith(PS)){
	    //if classpath startswith Pathseparator remove it
	    moclasspath=moclasspath.substring(1,moclasspath.length());
	}
	if(moclasspath.trim().indexOf(projpath)!=-1){
	    //if projpath is already found eliminate it
	    projpath="";
	}
	classpath=classpath+PS+projpath+PS+moclasspath;
	compile.compileClicked(SourcePath+".java",options.getAttribute("JDKPath"),classpath,f.toString());
	compileStatus=compile.getCompileStatus();
    }

    public String getClasspath()
    {
	String moclasspath=null;
	File f=new File("."+FS+"projects"+FS+"MOObjects.xml");
	Document doc1=(Document)tCon.getTransverseComponent("XMLMODEL");
	Element superelement=(Element)doc1.getElementsByTagName("CLASS").item(0);
	String superStr=superelement.getAttribute("extends");
	try
	    {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(f);
		NodeList classnodes=doc.getElementsByTagName("CLASS");
		for(int i=0;i<classnodes.getLength();i++){
		    Element reqnode=(Element)classnodes.item(i);
		    if(reqnode.getAttribute("name").equals(superStr)){
			moclasspath=reqnode.getAttribute("classpath");
			break;
		    }
		}
	    }
	catch(Exception e){
	    e.printStackTrace();
	}
	return moclasspath;
    }
					
			

    public void optButton_Clicked(ActionEvent ae)
    {
	callCompileOptions();
    }

    public void callCompileOptions()
    {
	COptions copt=new COptions((JDialog)getParentContainer(this),resourceBundle.getString("Compile Status"));
	copt.init();
	if(copt.readXML()){
	    copt.initialize();
	}
	copt.setLocation(getScrLoc(copt));
	copt.setVisible(true);
    }
    public void saveButton_Clicked(ActionEvent ae)
    {
	try{
	    File f=new File(SourcePath+".java");
	    FileOutputStream fout=new FileOutputStream(f);
	    fout.write(JavaSource.getText().getBytes());
	    fout.close();
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Saved file : {0}.java"),new String[]{new File(SourcePath).getName()}),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
	compileButton.setEnabled(true);
    }

    public void clearButton_Clicked(ActionEvent ae)
    {
	errorArea.setText("");
    }
    public void setLoadStatus(boolean status)
    {
	isLoaded=status;
    }

    public void loadScreenData()
    {
	File f=new File("."+FS+"projects"+FS+"MOObjects.xml");
	if(!f.exists()){
	    xmlwrite.populate();
	}
	//get the packagename and the classname and construct the entire sourcepath
	String filePath=(String)tCon.getTransverseComponent("currentProjectPath");
	Document doc=(Document)tCon.getTransverseComponent("XMLMODEL");
	Element node=(Element)doc.getElementsByTagName("CLASS").item(0);
	classname=node.getAttribute("name");
	SourcePath=filePath+node.getAttribute("name");
	f=new File(SourcePath);
	if(!f.exists()){
	    f.mkdir();
	}
	SourcePath=SourcePath+FS+node.getAttribute("name");
	packagename=node.getAttribute("package");
	/*if(isLoaded){
	  backupSource(SourcePath,filePath,packagename,classname);
	  }*/
	writeXMLFile(doc.getDocumentElement(),filePath+classname+".mow");
	String source=null;
	try {
	    char []cBuff=null;
	    if(!firstTime) {
		// The firstTime flag is set to enable tagging while merging
		// and to pass null for the first time a java is generated. 
		File tempFile=new File(SourcePath+".java");
		int length=(int)tempFile.length();
		char[] tmp = new char[length];
		FileReader fRead=new FileReader(SourcePath+".java");
		int size = fRead.read(tmp,0,length);
        cBuff = new char[size];
        System.arraycopy(tmp, 0, cBuff, 0, size);
		fRead.close();    
	    }	
	    ClassInfo clsInfo=new ClassInfo(SourcePath+".config",SourcePath+".java");
	    source=clsInfo.generateSource(doc);
	    String mergedSource=null;
	    if(firstTime) {
		mergedSource = clsInfo.mergeSource(source,null);
	    }
	    else {
		mergedSource = clsInfo.mergeSource(source,new String(cBuff));
	    }	
	    FileOutputStream fout=new FileOutputStream(SourcePath+".java");
	    fout.write(mergedSource.getBytes());
	    fout.close();    

	    File tempFile=new File(SourcePath+".java");
	    int length=(int)tempFile.length();
		char[] tmp = new char[length];
		FileReader fRead=new FileReader(SourcePath+".java");
		int size = fRead.read(tmp,0,length);
        cBuff = new char[size];
        System.arraycopy(tmp, 0, cBuff, 0, size);
    
		fRead.close();  
	    JavaSource.setText(new String(cBuff));
	    JavaSource.setCaretPosition(0);
	}
	catch(Exception e){
	    e.printStackTrace();
	}
    }

    /*public void backupSource(String scrpath,String filepath,String packname,String clsname)
      {
      This method has been laid off for now...It is used for maintaining ths source and class files while modifying
      try{
      File f=new File(scrpath+".java");
      tempsource=new byte[(int)f.length()];
      FileInputStream fin=new FileInputStream(f);
      fin.read(tempsource);
      fin.close();
      f=new File(filepath+"classes"+FS+packname.replace('.',FS.charAt(0))+FS+clsname+".class");
      tempclass=new byte[(int)f.length()];
      fin=new FileInputStream(f);
      fin.read(tempclass);
      fin.close();
      }
      catch(Exception e){
      e.printStackTrace();
      }

      }*/

    public void writeXMLFile(Node node,String path)
    {
	try {
	    TransformerFactory tff=TransformerFactory.newInstance();
	    Transformer tf=tff.newTransformer();
	    DOMSource   dSource=new DOMSource(node);
	    StreamResult sResult=new StreamResult(path);
	    tf.transform(dSource,sResult);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }
    public int nextActionPerformed(String str)
    {
	return 1;
    }
    public int previousActionPerformed(String str)
    {
	firstTime=false;
	errorArea.setText("");
	return 1;
    }
    public boolean finishActionPerformed()
    {
	if(compileStatus!=0){
	    JOptionPane.showMessageDialog(null,resourceBundle.getString("Please compile the source"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
	    return false;
	}
	//callOIDUpdation();
	callXMLUpdation();
	getParentContainer(this).setVisible(false);
	return true;
    }

    public void callXMLUpdation()
    {
	File f=new File("."+FS+"projects"+FS+"MOObjects.xml");
	try{
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    Document doc=db.parse(f);
	    NodeList options=doc.getElementsByTagName("CLASS");
	    for(int i=0;i<options.getLength();i++){
		Element child=(Element)options.item(i);
		//If node already exists return;
		if(child.getAttribute("name").equals(packagename+"."+classname) && child.getAttribute("classpath").equals(classpath)){
		    return;
		}

		if(child.getAttribute("name").equals(packagename+"."+classname) && !child.getAttribute("classpath").equals(classpath)){
		    //if node already exists and classpath is different update the Document...check for both the class and packagename
		    child.setAttribute("classpath",classpath);
		    xmlwrite.updateXML(doc,f);
		    return;
		}
	    }
	    //else update both the classname and its classpath
	    Element newchild=doc.createElement("CLASS");
	    newchild.setAttribute("name",packagename+"."+classname);
	    newchild.setAttribute("classpath",classpath);
	    doc.getDocumentElement().appendChild(newchild);
	    xmlwrite.updateXML(doc,f);
	}
	catch(Exception e){
	    e.printStackTrace();
	    return;
	}
    }

    public void callOIDUpdation()
    {
	try {
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    Document doc=db.parse(getNmsHome()+"conf"+File.separator+"OIDType.data");
	    Document newDoc=(Document)tCon.getTransverseComponent("XMLMODEL");
	    Element  newData=(Element)newDoc.getElementsByTagName("DEVICE_PARAMS").item(0);
	    if(newData==null)  {
		// If Add Device Screen is Disabled return.
		return;
	    }
	    Element  userTester=(Element)newDoc.getElementsByTagName("USER_TESTER").item(0);
	    Element  discFilter=(Element)newDoc.getElementsByTagName("DISC_FILTER").item(0);
	    NodeList dataList=doc.getElementsByTagName("DATA");
	    int result;
	    boolean updateNeeded=false;
	    boolean avoidWrite=false;
	    Element data=null;
	    for(int i=0;i<dataList.getLength();i++) {
		data=(Element)dataList.item(i);
		result=compareElements(data,newData);
		if(result==-1) {
		    // Since OID of the two nodes are not equal further 
		    // comparison is not necessary.  
		    continue;
		}
		else {
		    // This is will happen when the user has entered a new 
		    // entry.  
		    avoidWrite=true;
		}
		if(result==0) {
		    // Update to doc (Document) object
		    data.setAttribute("OID",newData.getAttribute("eoid"));
		    data.setAttribute("TYPE",newData.getAttribute("devType"));
		    data.setAttribute("POLL_INTERVAL",newData.getAttribute("pollInt"));
		    if(userTester!=null) {
			data.setAttribute("USER_TESTER",userTester.getAttribute("className"));
		    }
		    if(discFilter!=null) {
			data.setAttribute("DISC_FILTER",discFilter.getAttribute("className"));
		    }
		    updateNeeded=true;
		}
		if(!compareTester(data,userTester) || !compareFilter(data,discFilter)) {
		    if(userTester==null) {
			data.setAttribute("USER_TESTER","");
		    }
		    if(discFilter==null) {
			data.setAttribute("DISC_FILTER","");
		    }	
		    updateNeeded=true;
		}
	    }
	    if(!avoidWrite) {
		Element newNode=doc.createElement("DATA");
		newNode.setAttribute("OID",newData.getAttribute("eoid"));
		newNode.setAttribute("TYPE",newData.getAttribute("devType"));
		newNode.setAttribute("POLL_INTERVAL",newData.getAttribute("pollInt"));
		if(userTester!=null) {
		    newNode.setAttribute("USER_TESTER",userTester.getAttribute("className"));
		}
		if(discFilter!=null) {
		    newNode.setAttribute("DISC_FILTER",discFilter.getAttribute("className"));
		}
		doc.getDocumentElement().appendChild(newNode);
	    }
	    if(updateNeeded || !avoidWrite) {
		// write OIDType.data and add an entry into it.  
		writeXMLFile(doc,getNmsHome()+"conf"+File.separator+"OIDType.data","ISO-8859-1","OIDType.data.dtd");
	    }
	}
	catch(Exception e) {
	    JOptionPane.showMessageDialog(null,resourceBundle.getString("Exception :")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
	    return;
	}
    }

    private int compareElements(Element nodeFromOIDType,Element nodeFromScr) {
	// Return type 
	// -1 : if two nodes are not equal.
	//  0 : only when both node have oid,type and poll Interval are equal 
	//      and needs updation.
	//  1 : when it has compared and found needs no change.

	if(!nodeFromOIDType.getAttribute("OID").trim().equals(nodeFromScr.getAttribute("eoid").trim())) {
	    // If OID is different further checking need not be done.  
	    return -1;
	}
	if(!nodeFromOIDType.getAttribute("TYPE").trim().equals(nodeFromScr.getAttribute("devType").trim())) {
	    return 0;
	}
	if(!nodeFromOIDType.getAttribute("POLL_INTERVAL").trim().equals(nodeFromScr.getAttribute("pollInt").trim())) {
	    return 0;
	}
	return 1;
    }
    private boolean compareFilter(Element nodeFromOIDType,Element filter) {
	if(nodeFromOIDType.getAttribute("DISC_FILTER")==null && filter==null) {
	    return true;
	}
	if(nodeFromOIDType.getAttribute("DISC_FILTER")!=null && filter!=null) {
	    if(nodeFromOIDType.getAttribute("DISC_FILTER").trim().equals(filter.getAttribute("className"))) {
		return true;
	    }
	}
	return false;
    }
    private boolean compareTester(Element nodeFromOIDType,Element tester) {
	if(nodeFromOIDType.getAttribute("USER_TESTER")==null && tester==null) {
	    return true;
	}
	if(nodeFromOIDType.getAttribute("USER_TESTER")!=null && tester!=null) {
	    if(nodeFromOIDType.getAttribute("USER_TESTER").trim().equals(tester.getAttribute("className").trim())) {
		return true;
	    }
	}
	return false;
    }
    private void writeXMLFile(Document doc,String filepath,String encoding,String dtdFileName) throws Exception {
	TransformerFactory tff=TransformerFactory.newInstance();
	Transformer tf=tff.newTransformer();
	tf.setOutputProperty("doctype-system",dtdFileName);
	tf.setOutputProperty("encoding",encoding);
	DOMSource dSource=new DOMSource(doc.getDocumentElement());
	StreamResult sResult=new StreamResult(filepath);
	tf.transform(dSource,sResult);
    }

    public String getNmsHome()
    {
	return "."+File.separator;
    }
    public void cancelActionPerformed(String str)
    {
	if(str.trim().equals("Screen6")){
	    if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("MOGeneration is not complete!!!!Do You want to quit"),resourceBundle.getString("Error"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
		Document doc=(Document)tCon.getTransverseComponent("XMLMODEL");
		Element node=(Element)doc.getElementsByTagName("CLASS").item(0);
		if(!isLoaded){
		    String filepath=(String)tCon.getTransverseComponent("currentProject");
		    deleteAllFiles(filepath+"classes");
		    deleteAllFiles(filepath+node.getAttribute("name"));
		}
		/*else{
		  try{
		  FileOutputStream fout=new FileOutputStream(new File(SourcePath+".java"));
		  fout.write(tempsource);
		  fout.close();
		  fout=new FileOutputStream(new File((String)tCon.getTransverseComponent("currentProjectPath")+"classes"+FS+node.getAttribute("package").replace('.',FS.charAt(0))+FS+node.getAttribute("name")+".class"));
		  fout.write(tempclass);
		  fout.close();
		  }
		  catch(Exception e){
		  e.printStackTrace();
		  }
		  }*/
		getParentContainer(this).setVisible(false);
	    }
	}
    }
    public void closeActionPerformed()
    {
    }
    public void deleteAllFiles(String filename)
    {
	File f=new File(filename);
	File[] f1=f.listFiles();
	if(f1 != null){
	    for(int i=0;i<f1.length;i++)
		{
		    if(f1[i].isFile()){	
			f1[i].delete();
		    }
		    else{
			File filepath=new File(f1[i].getAbsolutePath());
			deleteAllFiles(f1[i].getAbsolutePath());
			filepath.delete();
		    }
		}
	}
	if(f.exists()){
	    f.delete();
	}
	if((f=new File(filename+".mow")).exists()){
	    f.delete();
	}
    }

    public Point getScrLoc(Container con)
    {
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	int width=(int)((d.getWidth()-con.getSize().getWidth())/2);
	int height=(int)((d.getHeight()-con.getSize().getHeight())/2);
	return new Point(width,height);
    }

    public Container getParentContainer(Container con)
    {
	while(!(con instanceof JDialog)){
	    con=con.getParent();
	}
	return con;
    }

    public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
    { 
	//<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
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

	//<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
    }
}
