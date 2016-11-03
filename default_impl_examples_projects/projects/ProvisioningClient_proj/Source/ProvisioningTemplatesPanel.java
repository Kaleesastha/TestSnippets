//$Id: ProvisioningTemplatesPanel.java,v 1.3 2008/08/25 07:24:14 johnpaul Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;

import java.text.MessageFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;

import com.adventnet.nms.provisioning.messaging.*;
import com.adventnet.nms.provisioning.server.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.ListViewCellRenderer;
import 	com.adventnet.beans.probeans.ProListViewDefaultTableModel;
import 	com.adventnet.beans.probeans.ProTable;
import com.adventnet.nms.startclient.AbstractBaseNmsPanel;
import com.adventnet.nms.startclient.NmsMainApplet;
import com.adventnet.management.config.CommonUtil;
import test.provisioning.TemplateNmsFrame;
import com.adventnet.nms.util.NmsUiAPI; 
import com.adventnet.nms.startclient.MainPanel;
import com.adventnet.management.i18n.AdventNetResourceBundle;
import javax.swing.table.TableColumnModel;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import java.io.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;


public class ProvisioningTemplatesPanel extends AbstractBaseNmsPanel implements MouseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.probeans.ProListView listView = null;
	//<End_Variable_Declarations>
	ProvisioningAPIImpl_SessionStub api ;
	ProListViewDefaultTableModel viewModel;
	ProTable table;
	Properties provisioningProperties = new Properties();
	DocumentBuilderFactory documentFactory;
	DocumentBuilder documentBuilder;
 	ListViewCellRenderer cellRenderer=null; 
	static boolean firstTime = true;
	public void init(JApplet a)
	{
		applet =a;
		this.init();
	}
	public void setProperties(Properties p)
	{
		provisioningProperties=p;
		if(firstTime)
		{
			reload();
			firstTime=false;
		}
	}


    public void stop()
  {
       //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void start()
  {
       //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void init()
  {
	  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+817,getPreferredSize().height+442)); 
        setSize(getPreferredSize()); 
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
        	String errorMessage = ex.getMessage() != null ? ex.getMessage() : ex.toString();	
			System.err.println(MessageFormat.format(resourceBundle.getString("Error in init method of the ProvisioningTemplatesPanel: {0}"), new Object[]{errorMessage})); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
	try
	{
		listView.setResourceBundle(AdventNetResourceBundle.getInstance());
		Vector columnNames=new Vector();
		columnNames.addElement(resourceBundle.getString("Template Name"));
		columnNames.addElement(resourceBundle.getString("Description"));
		viewModel= new ProListViewDefaultTableModel(columnNames);
		listView.setListViewModel(viewModel);
		listView.setBrowsePanelFont(NmsClientUtil.getFont());
		Color[] colors={new Color(223,223,223),new Color(223,223,223),new Color(223,223,223)};
		listView.setComponentBackground(colors);
		table=listView.getProTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		TableColumnModel colModel=table.getColumnModel();
		int colCount=colModel.getColumnCount();
		for(int i=0;i<colCount;i++){
			colModel.getColumn(i).setCellRenderer(cellRenderer);
		}

		api=new ProvisioningAPIImpl_SessionStub();
		documentFactory  = DocumentBuilderFactory.newInstance();
		documentBuilder = documentFactory.newDocumentBuilder();
		/*InputSource source=new InputSource(new StringReader("<Templates>"+api.getAllTemplates()+"</Templates>"));
		Document d=documentBuilder.parse(source);
		Element e=d.getDocumentElement();
		NodeList nl= e.getChildNodes();*/
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
	}
	catch(Exception e)
	{
        System.err.println(resourceBundle.getString("Error occured while creating Templates Panel"));
	}
	ProvClientUtils.englishToNative=AdventNetResourceBundle.getInstance();
  } 
      private void reload()
      {
	try
	{
		String tmpString = api.getAllTemplates();
		int index = tmpString.indexOf("<?xml version"); //No I18n
		while( index != -1)
		{
			StringBuffer buffer = new StringBuffer(tmpString);//No I18n
			buffer.delete(index, tmpString.indexOf(">", index)+1);//No I18n
			tmpString = buffer.toString();//No I18n
			index = tmpString.indexOf("<?xml version",index);//No I18n
		}
		InputSource source=new InputSource(new StringReader("<Templates>"+tmpString+"</Templates>"));//No I18n
		
		Document d=documentBuilder.parse(source);
		Element e=d.getDocumentElement();
		NodeList nl= e.getChildNodes();
		Vector dataVector=new Vector();
		for(int i=0;i<nl.getLength();i++)
		{
			if(nl.item(i) instanceof Element)
			{
				Element ele=(Element)nl.item(i);
				Vector rowVector=new Vector();
				rowVector.addElement(ele.getAttribute("name"));
				rowVector.addElement(ele.getAttribute("description"));
				dataVector.addElement(rowVector);
			}
		}
	    viewModel.setDataVector(dataVector);
		//viewModel.setFromIndex(1);
		int size = dataVector.size();
		if(size != 0)
		{
			viewModel.setStartIndex(1);
			viewModel.setEndIndex(size); //Issue ID 11372986			
		}
		//viewModel.setTotalCount(dataVector.size());
		//viewModel.setPageLength(dataVector.size()<25?dataVector.size():25);
		//int len=listView.getPageLengthComboBox().getItemCount();
		//for (int i=0;i<len;i++)listView.deletePageLengthForIndex(i);
		JComboBox jcb = listView.getPageLengthComponent().getComboBox();
		int len = jcb.getItemCount();
		for (int i=0;i<len;i++)listView.getPageLengthComponent().removeItemAt(i);

		//viewModel.refreshModel();
		viewModel.refresh();
		if (dataVector.size()==0)
		{
	    	//viewModel.setFromIndex(0);
			//viewModel.setStartIndex(0);
			viewModel.setViewVector(dataVector);
			listView.getButtonGroup().setEnabled(false);
		}
	}
	catch(Exception e)
	{
		System.err.println(resourceBundle.getString("Error while getting Template list"));
	}
      }
    	public String key()
	 {
		return "ProvisioningTemplatesPanel";
	}
	public String getPanelClassName()
	{
		return "com.adventnet.nms.provisioning.ui.ProvisioningTemplatesPanel";
	}
	public void actionPerformed(ActionEvent e)
	{
		String es=e.getActionCommand();
		if (es.equals("Provision"))
		{
			provision("provision");
		}
		else if (es.equals("Schedule"))
		{
			
			provision("scheduled provisioning");
		}
		else if (es.equals("Refresh"))
		{
			reload();
		}

        else if(es.equals("LayoutManager"))
        {	
            int r=table.getSelectedRow();
            if (r > -1)
            {
                try
                {	
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    int c=table.convertColumnIndexToView(0);
    				String tName=table.getValueAt(r,c).toString().trim();
                    ClientLayoutHandler handler = new ClientLayoutHandler(applet,tName,api);
                    handler.handleTemplate();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }catch(Exception exx)
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    System.err.println(resourceBundle.getString("Error occured while creating layout"));
                }
            }
			else
			{
                String message = resourceBundle.getString("Select a template for changing its layout");
				JOptionPane.showMessageDialog(this,message,resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}

        }

		else if (es.equals("View"))
		{
			int r=table.getSelectedRow();
			if (r>-1)
			{
				int c=table.convertColumnIndexToView(0);
				String tName=table.getValueAt(r,c).toString().trim();
				try
				{
					JPanel tempPanel=new JPanel();
					JButton b=new JButton(resourceBundle.getString("Close"));
					b.addActionListener
					(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent ev)
							{
								JButton s=(JButton)ev.getSource();
								s.getTopLevelAncestor().setVisible(false);
							}
						}
					);
					tempPanel.add(b);
                                        Template template = new Template(api.viewTemplate(tName));
					Element templateElement = template.getElement();
					transform(templateElement);
                                        
                                        Document tmpDoc = templateElement.getOwnerDocument();
                                        StringWriter writer = new StringWriter();
                                        try
                                        {
                                            TransformerFactory fac = TransformerFactory.newInstance();
                                            Transformer trans = fac.newTransformer();
                                            trans.setOutputProperty("indent","yes");//No I18N
                                            trans.setOutputProperty("encoding","UTF-8");//No I18N 
                                            DOMSource domSource = new DOMSource(tmpDoc);
                                            StreamResult streamResult = new StreamResult(writer);
                                            trans.transform(domSource, streamResult);
                                        }
                                        catch(Exception e1)
                                        {
                                            e1.printStackTrace();
                                        }

					JTextArea pane = new JTextArea(writer.toString());
					pane.setEditable(false);
					pane.setLineWrap(false);
					JScrollPane ed = new JScrollPane(pane);
					JDialog d=(new JOptionPane()).createDialog(this,MessageFormat.format(resourceBundle.getString("ProvisioningTemplate {0}"), new Object[]{tName}));
					d.getContentPane().removeAll();
					d.getContentPane().setLayout(new BorderLayout());
					d.getContentPane().add(ed,BorderLayout.CENTER);
					d.getContentPane().add(tempPanel,BorderLayout.SOUTH);
					d.setSize((int)(getWidth()*3/4),(int)(getHeight()*3/4));
					NmsClientUtil.centerWindow(d);
					d.setVisible(true);
					d.addWindowListener
					(
						new WindowAdapter()
						{
							public void windowDeactivated(WindowEvent e)
							{
								((JDialog)e.getSource()).removeWindowListener(this);
								((JDialog)e.getSource()).dispose();
							}
							public void windowClosed(WindowEvent e)
							{
								((JDialog)e.getSource()).removeWindowListener(this);
								((JDialog)e.getSource()).dispose();
							}
						}
					);

				}	
				catch(Exception exception)
				{
					JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Error occured while fetching the template {0}"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Select the template to be viewed"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (es.equals("ActivityList"))
		{
			int r=table.getSelectedRow();
			if (r>-1)
			{
				int c=table.convertColumnIndexToView(0);
				String tName=table.getValueAt(r,c).toString().trim();
				ProvisioningOperationsPanel pop=(ProvisioningOperationsPanel)NmsUiAPI.getNmsPanelInstance("ProvisioningOperationsPanel");
				String oldName=pop.getSelectedTemplateName();
				pop.setSelectedTemplateName(tName);
				Vector v=pop.getVectorOfTuples();
				if (v.size()>0)
				{
					NmsClientUtil.getMainPanel().showPanel("ActivityList");
					pop.reloadTable(v);
					pop.addReloadAllMenuItem();
				}
				else
				{
					pop.setSelectedTemplateName(oldName);
					JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("No activities provisioned for {0} template"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Select the template to view its activities"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}

		}
		else if (es.equals("Delete"))
		{
			int r=table.getSelectedRow();
			if (r>-1)
			{
				int c=table.convertColumnIndexToView(0);
				String tName=table.getValueAt(r,c).toString().trim();
				int ok=JOptionPane.showConfirmDialog(this, MessageFormat.format(resourceBundle.getString("Template {0} will permanently be deleted from server.Do you want to continue ?"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (ok==JOptionPane.YES_OPTION)
				{
					try
					{
						if (api.deleteTemplate(tName))
						{
							JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Template {0} deleted successfully"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Deleting the template {0} failed"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
						}
						reload();
					}	
					catch(Exception exception)
					{
						JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Error occured while deleting the template {0}"), new Object[]{tName}),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Select the template to be deleted"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(es.equals("provTemplatesHelp"))
		{
			NmsClientUtil.showHelpBasedOnKey(key());
		}
	}
	private void transform(Element e)
	{
            Document d=e.getOwnerDocument();            
                
                NodeList nl=e.getElementsByTagName("*");
                for(int i=0;i<nl.getLength();i++)
                {
                        if (nl.item(i) instanceof Element)
                        {
                                Element thisElement = (Element)nl.item(i);
                                thisElement.getParentNode().insertBefore(d.createTextNode("\n"),thisElement);
		        if (thisElement.getElementsByTagName("*").getLength()>0)
		        {
			    thisElement.appendChild(d.createTextNode("\n"));
		        }

                        }
                }
	    e.appendChild(d.createTextNode("\n"));
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ProvisioningClientResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

  
  //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        listView= new com.adventnet.beans.probeans.ProListView();

  
        //<End_initVariables>
	cellRenderer=new ListViewCellRenderer();
	changeNavigationButtons();
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(listView);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.err.println(MessageFormat.format(resourceBundle.getString("Internal Error in ProvisioningTemplatesPanel: {0}"), new Object[]{message}));
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.err.println(MessageFormat.format(resourceBundle.getString("Internal Error in ProvisioningTemplatesPanel: {0}"), new Object[]{message}));
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }



  





  public ProvisioningTemplatesPanel()
  {
    //<Begin_ProvisioningTemplatesPanel>
    //this.init();
	 this.start();
  
    //<End_ProvisioningTemplatesPanel>
  }

  public ProvisioningTemplatesPanel(java.applet.Applet applet)
  {
    //<Begin_ProvisioningTemplatesPanel_java.applet.Applet>
    this.applet = applet;
    //this.init();
	 this.start(); 
  
    //<End_ProvisioningTemplatesPanel_java.applet.Applet>
  }

	public  void mouseEntered(MouseEvent e) 
	{
	}
	public  void mouseExited(MouseEvent e) 
	{
	}
	public  void mousePressed(MouseEvent e) 
	{
	}
	public  void mouseReleased(MouseEvent e) 
	{
	}
	public  void mouseClicked(MouseEvent me) 
	{
		Object source=me.getSource();
		if(source instanceof JTable)
		{
			if(me.isMetaDown())
			{
				JTable table = (JTable)source;
				Point point = me.getPoint();
				int row = table.rowAtPoint(point);
				if(row != -1)
				{
					table.setRowSelectionInterval(row, row);
					JPopupMenu popupMenu = getTablePopupMenu();
					if(popupMenu != null)
					{
						SwingUtilities.convertPointToScreen(point, table);	
						Dimension scr_dim =	Toolkit.getDefaultToolkit().getScreenSize();
						if((point.getX() + popupMenu.getPreferredSize().getWidth()) > scr_dim.getWidth())
						{								
							point.setLocation((point.getX() - popupMenu.getPreferredSize().getWidth()), point.getY());
						}
						if((point.getY() + popupMenu.getPreferredSize().getHeight()) > scr_dim.getHeight())
						{	
							point.setLocation(point.getX(), (point.getY() - popupMenu.getPreferredSize().getHeight()));
						}
						SwingUtilities.convertPointFromScreen(point, table);
						popupMenu.show(table, (int)point.getX(), (int)point.getY());
					}
				}
			}
			else if(me.getClickCount()==2)
			{
				provision("provision");
			}
		}
	}
	
	private JPopupMenu getTablePopupMenu()
	{
		StringTokenizer tokenizer = new StringTokenizer((String)getCurrentNodeProperties().get("TABLE-POPUP-MENU"), ",");

		JPopupMenu popupMenu = null;	
		
		if(tokenizer.countTokens() > 0)
		{
			JMenuBar panelMenuBar = getPanelMenuBar();
			
			Vector menus = new Vector();

			while(tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken().trim();

				for(int i=0; i<panelMenuBar.getMenuCount(); i++)
				{
					JMenu menu = panelMenuBar.getMenu(i);

					if(menu.getText().equals(ProvClientUtils.getString(token)))
					{
						menus.addElement(menu);
					}
				}	
			}

			if(menus.size() > 0)
			{
				popupMenu = new JPopupMenu();

				if(menus.size() == 1)	
				{
					JMenu menu = (JMenu)menus.elementAt(0);

					if(menu != null)
					{
						int component_count = menu.getMenuComponentCount();

						for(int j=0; j<component_count; j++)
						{
							Component component  = menu.getMenuComponent(0);

							popupMenu.add(component);
							if ((component instanceof JMenuItem)&&(((JMenuItem)component).getText().equals(ProvClientUtils.getString("Refresh"))))
							{
								popupMenu.remove(component);
							}
						}
					}

				}
				else
				{
					for(int i=0; i<menus.size(); i++)
					{
						JMenu menu = (JMenu)menus.elementAt(i);

						if(menu != null)
						{
							popupMenu.add(menu);

							if(i < (menus.size() - 1))
							{
								popupMenu.addSeparator();
							}
						}
					}
				}
			}
		}

		return popupMenu;
	}
	private void provision(String errMessage)
	{
		int r=table.getSelectedRow();
		if (r>-1)
		{
			int c=table.convertColumnIndexToView(0);
			String tName=table.getValueAt(r,c).toString().trim();
			if (applet instanceof NmsMainApplet)
			{
				NmsMainApplet nma= (NmsMainApplet)applet;
				nma.setParameter("TemplateName",tName);
				TemplateNmsFrame tnf =new TemplateNmsFrame(true);
				tnf.init(nma);
				tnf.setVisible(true);
				if (errMessage.equals("scheduled provisioning"))
				{
					tnf.setScheduled(true);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Select a Template to do {0}"), new Object[]{errMessage}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void changeNavigationButtons(){
		String imageDir=null;
		Icon[] imageIcons=new Icon[6];
		if(NmsClientUtil.applet != null)
		{
			imageDir = NmsClientUtil.applet.getDocumentBase () +    "../images";//No Internationalisation
		}

		//String imageDir = NmsClientUtil.applet.getDocumentBase () +   "../images";//No Internationalisation
		try
		{
			JButton button=listView.getButtonGroup().getFirstButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

			button=listView.getButtonGroup().getLastButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);


			button=listView.getButtonGroup().getNextButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

			button=listView.getButtonGroup().getPreviousButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back1" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back1_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

		
		}
		catch(Exception e){
			NmsClientUtil.err(null,NmsClientUtil.GetString("Images not found : ") + e.getMessage()); //No Internationalization
			e.printStackTrace();
		}
	}


}




