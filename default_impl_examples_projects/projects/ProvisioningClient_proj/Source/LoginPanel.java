//$Id: LoginPanel.java,v 1.1 2006/08/29 13:57:01 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;




public class LoginPanel extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JRadioButton rmiRadioButton = null;
	javax.swing.JRadioButton sessionRadioButton = null;
	javax.swing.JRadioButton jmsRadioButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel userNameLabel = null;
	javax.swing.JTextField userNameText = null;
	javax.swing.JLabel passwordLabel = null;
	javax.swing.JPasswordField passwordField = null;
	javax.swing.JLabel hostLabel = null;
	javax.swing.JTextField hostText = null;
	javax.swing.JLabel tcpPortLabel = null;
	javax.swing.JTextField tcpPortText = null;
	javax.swing.JLabel rmiPortLabel = null;
	javax.swing.JTextField rmiPortText = null;
	javax.swing.JLabel languageLabel = null;
	javax.swing.JComboBox languageComboBox = null;
	javax.swing.JLabel countryLabel = null;
	javax.swing.JComboBox countryComboBox = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private     ButtonGroup bg =new ButtonGroup();






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
        setPreferredSize(new Dimension(getPreferredSize().width+614,getPreferredSize().height+441));
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
          showStatus(resourceBundle.getString("Error in init method"),ex);
        }
        // let us set the initialzed variable to true so
        // we dont initialize again even if init is called
        initialized = true;


         //<End_init>
	bg.add(rmiRadioButton);
	bg.add(sessionRadioButton);
	bg.add(jmsRadioButton);
	if (applet!=null)
	{
		jmsRadioButton.setVisible(false);
		rmiSelectedAction();
	}
	rmiRadioButton.setSelected(true);
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

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.BevelBorder(0),resourceBundle.getString("Provisioning Authentication"),0,0,new Font("Dialog",0,12),new Color(-13434829)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex);
          }

          try
          {
            JTextArea1.setEditable(false);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setText(resourceBundle.getString("Enter User Name and Password for Authentication.Default login root with password public for unconfigured system."));
            JTextArea1.setForeground(new Color(-16777216));
            JTextArea1.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex);
          }

          try
          {
            JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Connection Type"),0,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex);
          }

          try
          {
            rmiRadioButton.setFont(new Font("SansSerif",0,12));
            rmiRadioButton.setHorizontalTextPosition(4);
            rmiRadioButton.setHorizontalAlignment(2);
            rmiRadioButton.setVerticalAlignment(0);
            rmiRadioButton.setVerticalTextPosition(0);
            rmiRadioButton.setText(resourceBundle.getString("RMI"));
            rmiRadioButton.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rmiRadioButton,ex);
          }

          try
          {
            sessionRadioButton.setFont(new Font("SansSerif",0,12));
            sessionRadioButton.setHorizontalTextPosition(4);
            sessionRadioButton.setHorizontalAlignment(2);
            sessionRadioButton.setVerticalAlignment(0);
            sessionRadioButton.setVerticalTextPosition(0);
            sessionRadioButton.setText(resourceBundle.getString("SESSION"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+sessionRadioButton,ex);
          }

          try
          {
            jmsRadioButton.setFont(new Font("SansSerif",0,12));
            jmsRadioButton.setHorizontalTextPosition(4);
            jmsRadioButton.setHorizontalAlignment(2);
            jmsRadioButton.setVerticalAlignment(0);
            jmsRadioButton.setVerticalTextPosition(0);
            jmsRadioButton.setText(resourceBundle.getString("JMS"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jmsRadioButton,ex);
          }

          try
          {
            JPanel6.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Connection Parameters"),0,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex);
          }

          try
          {
            userNameLabel.setHorizontalAlignment(2);
            userNameLabel.setFont(new Font("SansSerif",0,12));
            userNameLabel.setForeground(new Color(-16777216));
            userNameLabel.setHorizontalTextPosition(4);
            userNameLabel.setText(resourceBundle.getString("User name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+userNameLabel,ex);
          }

          try
          {
            userNameText.setHorizontalAlignment(2);
            userNameText.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+userNameText,ex);
          }

          try
          {
            passwordLabel.setHorizontalAlignment(2);
            passwordLabel.setFont(new Font("SansSerif",0,12));
            passwordLabel.setForeground(new Color(-16777216));
            passwordLabel.setHorizontalTextPosition(4);
            passwordLabel.setText(resourceBundle.getString("Password"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+passwordLabel,ex);
          }

          try
          {
            passwordField.setHorizontalAlignment(2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+passwordField,ex);
          }

          try
          {
            hostLabel.setHorizontalAlignment(2);
            hostLabel.setFont(new Font("SansSerif",0,12));
            hostLabel.setForeground(new Color(-16777216));
            hostLabel.setHorizontalTextPosition(4);
            hostLabel.setText(resourceBundle.getString("Host"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hostLabel,ex);
          }

          try
          {
            hostText.setHorizontalAlignment(2);
            hostText.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+hostText,ex);
          }
if(applet==null)
            {
              hostText.setText("localhost");
            }
            else
            {
              hostText.setText(applet.getDocumentBase().getHost());
              hostText.setEditable(false);
            }

          try
          {
            tcpPortLabel.setHorizontalAlignment(2);
            tcpPortLabel.setFont(new Font("SansSerif",0,12));
            tcpPortLabel.setForeground(new Color(-16777216));
            tcpPortLabel.setHorizontalTextPosition(4);
            tcpPortLabel.setText(resourceBundle.getString("Web Server Port"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tcpPortLabel,ex);
          }

          try
          {
            tcpPortText.setHorizontalAlignment(2);
            tcpPortText.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tcpPortText,ex);
          }
if(applet==null)
            {
             tcpPortText.setText("9090");
            }
            else
            {
             tcpPortText.setText(applet.getDocumentBase().getPort()+"");
             tcpPortText.setEditable(false);
            }

          try
          {
            rmiPortLabel.setHorizontalAlignment(2);
            rmiPortLabel.setFont(new Font("SansSerif",0,12));
            rmiPortLabel.setForeground(new Color(-16777216));
            rmiPortLabel.setHorizontalTextPosition(4);
            rmiPortLabel.setText(resourceBundle.getString("RMI Port"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rmiPortLabel,ex);
          }

          try
          {
            rmiPortText.setHorizontalAlignment(2);
            rmiPortText.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rmiPortText,ex);
          }
if(applet==null)
            {
             rmiPortText.setText("1099");
            }
            else
            {
             String rmiport=applet.getParameter("RMI_REG_PORT");
             if ((rmiport !=null)&&(rmiport.trim().length()>0))
             {
              rmiPortText.setText(rmiport);
             }
             else
             {
              rmiPortText.setText("1099");
             }
            }

          try
          {
			languageLabel.setHorizontalAlignment(2);
            languageLabel.setFont(new Font("SansSerif",0,12));
            languageLabel.setForeground(new Color(-16777216));
            languageLabel.setHorizontalTextPosition(4);
            languageLabel.setText(resourceBundle.getString("Language"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+languageLabel,ex);
          }

          try
          {
            languageComboBox.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+languageComboBox,ex);
          }
languageComboBox.addItem("ab(Abkhazian)");
            languageComboBox.addItem("aa(Afar)");
            languageComboBox.addItem("af(Afrikaans)");
            languageComboBox.addItem("sq(Albanian)");
            languageComboBox.addItem("am(Amharic)");
            languageComboBox.addItem("ar(Arabic)");
            languageComboBox.addItem("hy(Armenian)");
            languageComboBox.addItem("as(Assamese)");
            languageComboBox.addItem("ay(Aymara)");
            languageComboBox.addItem("az(Azerbaijani)");
            languageComboBox.addItem("ba(Bashkir)");
            languageComboBox.addItem("eu(Basque)");
            languageComboBox.addItem("bn(Bengali)");
            languageComboBox.addItem("dz(Bhutani)");
            languageComboBox.addItem("bh(Bihari)");
            languageComboBox.addItem("bi(Bislama)");
            languageComboBox.addItem("br(Breton)");
            languageComboBox.addItem("bg(Bulgarian)");
            languageComboBox.addItem("my(Burmese)");
            languageComboBox.addItem("be(Byelorussian)");
            languageComboBox.addItem("km(Cambodian)");
            languageComboBox.addItem("ca(Catalan)");
            languageComboBox.addItem("zh(Chinese)");
            languageComboBox.addItem("co(Corsican)");
            languageComboBox.addItem("hr(Croatian)");
            languageComboBox.addItem("cs(Czech)");
            languageComboBox.addItem("nl(Dutch)");
            languageComboBox.addItem("da(Danish)");
            languageComboBox.addItem("en(English)");
            languageComboBox.addItem("eo(Esperanto) ");
            languageComboBox.addItem("et(Estonian) ");
            languageComboBox.addItem("fo(Faeroese)");
            languageComboBox.addItem("fj(Fiji)");
            languageComboBox.addItem("fi(Finnish)");
            languageComboBox.addItem("fy(Frisian)");
            languageComboBox.addItem("fr(French)");
            languageComboBox.addItem("gl(Galician)");
            languageComboBox.addItem("ka(Georgian)");
            languageComboBox.addItem("de(German)");
            languageComboBox.addItem("el(Greek)");
            languageComboBox.addItem("kl(Greenlandic)");
            languageComboBox.addItem("gn(Guarani)");
            languageComboBox.addItem("gu(Gujarati)");
            languageComboBox.addItem("ha(Hausa)");
            languageComboBox.addItem("hi(Hindi)");
            languageComboBox.addItem("iw(Hebrew)");
            languageComboBox.addItem("hu(Hungarian)");
            languageComboBox.addItem("is(Icelandic)");
            languageComboBox.addItem("in(Indonesian)");
            languageComboBox.addItem("ia(Interlingua)");
            languageComboBox.addItem("ie(Interlingue)");
            languageComboBox.addItem("ik(Inupiak)");
            languageComboBox.addItem("ga(Irish)");
            languageComboBox.addItem("it(Italian)");
            languageComboBox.addItem("ja(Japanese)");
            languageComboBox.addItem("jw(Javanese)");
            languageComboBox.addItem("kn(Kannada)");
            languageComboBox.addItem("ks(Kashmiri)");
            languageComboBox.addItem("kk(Kazakh)");
            languageComboBox.addItem("rw(Kinyarwanda)");
            languageComboBox.addItem("ky(Kirghiz)");
            languageComboBox.addItem("rn(Kirundi)");
            languageComboBox.addItem("ko(Korean)");
            languageComboBox.addItem("ku(Kurdish)");
            languageComboBox.addItem("lo(Laothian)");
            languageComboBox.addItem("la(Latin)");
            languageComboBox.addItem("lv(Latvian)");
            languageComboBox.addItem("ln(Lingala)");
            languageComboBox.addItem("lt(Lithuanian)");
            languageComboBox.addItem("mk(Macedonian)");
            languageComboBox.addItem("mg(Malagasy)");
            languageComboBox.addItem("ms(Malay)");
            languageComboBox.addItem("ml(Malayalam)");
            languageComboBox.addItem("mt(Maltese)");
            languageComboBox.addItem("mi(Maori)");
            languageComboBox.addItem("mr(Marathi)");
            languageComboBox.addItem("mo(Moldavian)");
            languageComboBox.addItem("mn(Mongolian)");
            languageComboBox.addItem("na(Nauru)");
            languageComboBox.addItem("ne(Nepali)");
            languageComboBox.addItem("no(Norwegian)");
            languageComboBox.addItem("oc(Occitan)");
            languageComboBox.addItem("or(Oriya)");
            languageComboBox.addItem("om(Oromo (Afan))");
            languageComboBox.addItem("ps(Pashto (Pushto))");
            languageComboBox.addItem("fa(Persian)");
            languageComboBox.addItem("pl(Polish)");
            languageComboBox.addItem("pt(Portuguese)");
            languageComboBox.addItem("pa(Punjabi)");
            languageComboBox.addItem("qu(Quechua)");
            languageComboBox.addItem("rm(Rhaeto (Romance))");
            languageComboBox.addItem("ro(Romanian)");
            languageComboBox.addItem("ru(Russian)");
            languageComboBox.addItem("sm(Samoan)");
            languageComboBox.addItem("sg(Sangro)");
            languageComboBox.addItem("sa(Sanskrit)");
            languageComboBox.addItem("gd(Scots Gaelic)");
            languageComboBox.addItem("sr(Serbian)");
            languageComboBox.addItem("sh(Serbo Croatian)");
            languageComboBox.addItem("st(Sesotho)");
            languageComboBox.addItem("tn(Setswana)");
            languageComboBox.addItem("sn(Shona)");
            languageComboBox.addItem("sd(Sindhi)");
            languageComboBox.addItem("si(Singhalese)");
            languageComboBox.addItem("ss(Siswati)");
            languageComboBox.addItem("sk(Slovak)");
            languageComboBox.addItem("sl(Slovenian)");
            languageComboBox.addItem("so(Somali)");
            languageComboBox.addItem("es(Spanish)");
            languageComboBox.addItem("su(Sundanese)");
            languageComboBox.addItem("sw(Swahili)");
            languageComboBox.addItem("sv(Swedish)");
            languageComboBox.addItem("tl(Tagalog)");
            languageComboBox.addItem("tg(Tajik)");
            languageComboBox.addItem("ta(Tamil)");
            languageComboBox.addItem("tt(Tatar)");
            languageComboBox.addItem("te(Telugu)");
            languageComboBox.addItem("th(Thai)");
            languageComboBox.addItem("bo(Tibetan)");
            languageComboBox.addItem("ti(Tigrinya)");
            languageComboBox.addItem("to(Tonga)");
            languageComboBox.addItem("ts(Tsonga)");
            languageComboBox.addItem("tr(Turkish)");
            languageComboBox.addItem("tk(Turkmen)");
            languageComboBox.addItem("tw(Twi)");
            languageComboBox.addItem("uk(Ukrainian)");
            languageComboBox.addItem("ur(Urdu)");
            languageComboBox.addItem("uz(Uzbek)");
            languageComboBox.addItem("vi(Vietnamese)");
            languageComboBox.addItem("vo(Volapuk)");
            languageComboBox.addItem("cy(Welsh)");
            languageComboBox.addItem("wo(Wolof)");
            languageComboBox.addItem("xh(Xhosa)");
            languageComboBox.addItem("ji(Yiddish)");
            languageComboBox.addItem("yo(Yoruba)");
            languageComboBox.addItem("zu(Zulu)");
            languageComboBox.setSelectedItem("en(English)");
          try
          {
            countryLabel.setHorizontalAlignment(2);
            countryLabel.setFont(new Font("SansSerif",0,12));
            countryLabel.setForeground(new Color(-16777216));
            countryLabel.setHorizontalTextPosition(4);
            countryLabel.setText(resourceBundle.getString("Country"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+countryLabel,ex);
          }

          try
          {
            countryComboBox.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+countryComboBox,ex);
          }

            countryComboBox.addItem("AF(Afghanistan)");
            countryComboBox.addItem("AL(Albania)");
            countryComboBox.addItem("DZ(Algeria)");
            countryComboBox.addItem("AD(Andorra)");
            countryComboBox.addItem("AO(Angola)");
            countryComboBox.addItem("AI(Anguilla)");
            countryComboBox.addItem("AR(Argentina)");
            countryComboBox.addItem("AM(Armenia)");
            countryComboBox.addItem("AW(Aruba)");
            countryComboBox.addItem("AU(Australia)");
            countryComboBox.addItem("AT(Austria)");
            countryComboBox.addItem("AZ(Azerbaijan)");
            countryComboBox.addItem("BS(Bahamas)");
            countryComboBox.addItem("BH(Bahrain)");
            countryComboBox.addItem("BD(Bangladesh)");
            countryComboBox.addItem("BB(Barbados)");
            countryComboBox.addItem("BY(Belarus)");
            countryComboBox.addItem("BE(Belgium)");
            countryComboBox.addItem("BZ(Belize)");
            countryComboBox.addItem("BJ(Benin)");
            countryComboBox.addItem("BM(Bermuda)");
            countryComboBox.addItem("BT(Bhutan)");
            countryComboBox.addItem("BO(Bolivia)");
            countryComboBox.addItem("BA(Bosnia and Herzegovina)");
            countryComboBox.addItem("BW(Botswana)");
            countryComboBox.addItem("BR(Brazil)");
            countryComboBox.addItem("BN(Brunei Darussalam)");
            countryComboBox.addItem("BG(Bulgaria)");
            countryComboBox.addItem("BF(Burkina Faso)");
            countryComboBox.addItem("BI(Burundi)            ");
            countryComboBox.addItem("KH(Cambodia)");
            countryComboBox.addItem("CM(Cameroon)");
            countryComboBox.addItem("CA(Canada)             ");
            countryComboBox.addItem("CV(Cape Verde)");
            countryComboBox.addItem("CF(Central African Republic)");
            countryComboBox.addItem("TD(Chad)");
            countryComboBox.addItem("CL(Chile)");
            countryComboBox.addItem("CN(China)");
            countryComboBox.addItem("CO(Colombia)");
            countryComboBox.addItem("KM(Comoros)");
            countryComboBox.addItem("CG(Congo)");
            countryComboBox.addItem("CR(Costa Rica)");
            countryComboBox.addItem("CI(Cote D'ivoire)");
            countryComboBox.addItem("HR(Croatia)");
            countryComboBox.addItem("CU(Cuba)");
            countryComboBox.addItem("CY(Cyprus)");
            countryComboBox.addItem("CZ(Czech Republic)");
            countryComboBox.addItem("DK(Denmark)");
            countryComboBox.addItem("DJ(Djibouti)");
            countryComboBox.addItem("DM(Dominica)");
            countryComboBox.addItem("DO(Dominican Republic)");
            countryComboBox.addItem("TP(East Timor)");
            countryComboBox.addItem("EC(Ecuador)");
            countryComboBox.addItem("EG(Egypt)");
            countryComboBox.addItem("SV(El Salvador)");
            countryComboBox.addItem("GQ(Equatorial Guinea)");
            countryComboBox.addItem("ER(Eritrea)");
            countryComboBox.addItem("EE(Estonia)");
            countryComboBox.addItem("ET(Ethiopia)");
            countryComboBox.addItem("FJ(Fiji)");
            countryComboBox.addItem("FI(Finland)");
            countryComboBox.addItem("FR(France)");
            countryComboBox.addItem("GF(French Guiana)");
            countryComboBox.addItem("PF(French Polynesia)");
            countryComboBox.addItem("TF(French Southern Territories)");
            countryComboBox.addItem("GA(Gabon)");
            countryComboBox.addItem("GM(Gambia)");
            countryComboBox.addItem("GE(Georgia)");
            countryComboBox.addItem("DE(Germany)");
            countryComboBox.addItem("GH(Ghana)");
            countryComboBox.addItem("GR(Greece)");
            countryComboBox.addItem("GP(Guadeloupe)");
            countryComboBox.addItem("GT(Guatemala)");
            countryComboBox.addItem("GN(Guinea)");
            countryComboBox.addItem("GW(Guinea Bissau)");
            countryComboBox.addItem("GY(Guyana)");
            countryComboBox.addItem("HT(Haiti)");
            countryComboBox.addItem("HN(Honduras)");
            countryComboBox.addItem("HK(Hong Kong)");
            countryComboBox.addItem("HU(Hungary)");
            countryComboBox.addItem("IS(Iceland)");
            countryComboBox.addItem("IN(India)");
            countryComboBox.addItem("ID(Indonesia)");
            countryComboBox.addItem("IR(Iran)");
            countryComboBox.addItem("IQ(Iraq)");
            countryComboBox.addItem("IE(Ireland)");
            countryComboBox.addItem("IL(Israel)");
            countryComboBox.addItem("IT(Italy)");
            countryComboBox.addItem("JM(Jamaica)");
            countryComboBox.addItem("JP(Japan)");
            countryComboBox.addItem("JO(Jordan)");
            countryComboBox.addItem("KZ(Kazakhstan)");
            countryComboBox.addItem("KE(Kenya)");
            countryComboBox.addItem("KI(Kiribati)");
            countryComboBox.addItem("KP(North Korea)");
            countryComboBox.addItem("KR(South Korea)");
            countryComboBox.addItem("KW(Kuwait)");
            countryComboBox.addItem("KG(Kyrgyzstan)");
            countryComboBox.addItem("LA(Laos)");
            countryComboBox.addItem("LV(Latvia)");
            countryComboBox.addItem("LB(Lebanon)");
            countryComboBox.addItem("LS(Lesotho)");
            countryComboBox.addItem("LR(Liberia)");
            countryComboBox.addItem("LY(Libyan Arab Jamahiriya)");
            countryComboBox.addItem("LI(Liechtenstein)");
            countryComboBox.addItem("LT(Lithuania)");
            countryComboBox.addItem("LU(Luxembourg)");
            countryComboBox.addItem("MK(Macedonia)");
            countryComboBox.addItem("MG(Madagascar)");
            countryComboBox.addItem("MY(Malaysia)");
            countryComboBox.addItem("ML(Mali)");
            countryComboBox.addItem("MT(Malta)");
            countryComboBox.addItem("MQ(Martinique)");
            countryComboBox.addItem("MR(Mauritania)");
            countryComboBox.addItem("MU(Mauritius)");
            countryComboBox.addItem("YT(Mayotte)");
            countryComboBox.addItem("MX(Mexico)");
            countryComboBox.addItem("FM(Micronesia)");
            countryComboBox.addItem("MD(Moldova)");
            countryComboBox.addItem("MC(Monaco)");
            countryComboBox.addItem("MN(Mongolia)");
            countryComboBox.addItem("MS(Montserrat)");
            countryComboBox.addItem("MA(Morocco)");
            countryComboBox.addItem("MZ(Mozambique)");
            countryComboBox.addItem("MM(Myanmar)");
            countryComboBox.addItem("NA(Namibia)");
            countryComboBox.addItem("NP(Nepal)");
            countryComboBox.addItem("NL(Netherlands)");
            countryComboBox.addItem("AN(Netherlands Antilles)");
            countryComboBox.addItem("NC(New Caledonia)");
            countryComboBox.addItem("NZ(New Zealand)");
            countryComboBox.addItem("NI(Nicaragua)");
            countryComboBox.addItem("NE(Niger)");
            countryComboBox.addItem("NG(Nigeria)");
            countryComboBox.addItem("NU(Niue)");
            countryComboBox.addItem("NO(Norway)");
            countryComboBox.addItem("OM(Oman)");
            countryComboBox.addItem("PK(Pakistan)");
            countryComboBox.addItem("PA(Panama)");
            countryComboBox.addItem("PG(Papua New Guinea)");
            countryComboBox.addItem("PY(Paraguay)");
            countryComboBox.addItem("PE(Peru)               ");
            countryComboBox.addItem("PH(Philippines)");
            countryComboBox.addItem("PL(Poland)");
            countryComboBox.addItem("PT(Portugal)");
            countryComboBox.addItem("PR(Puerto Rico)");
            countryComboBox.addItem("QA(Qatar)");
            countryComboBox.addItem("RO(Romania)");
            countryComboBox.addItem("RU(Russian Federation)");
            countryComboBox.addItem("RW(Rwanda)");
            countryComboBox.addItem("SA(Saudi Arabia)");
            countryComboBox.addItem("SN(Senegal)");
            countryComboBox.addItem("SP(Serbia)");
            countryComboBox.addItem("SC(Seychelles)");
            countryComboBox.addItem("SL(Sierra Leone)");
            countryComboBox.addItem("SG(Singapore)");
            countryComboBox.addItem("SK(Slovakia)");
            countryComboBox.addItem("SI(Slovenia)");
            countryComboBox.addItem("SO(Somalia)");
            countryComboBox.addItem("ZA(South Africa)");
            countryComboBox.addItem("ES(Spain)");
            countryComboBox.addItem("LK(Sri Lanka)");
            countryComboBox.addItem("SD(Sudan)");
            countryComboBox.addItem("SR(Suriname)");
            countryComboBox.addItem("SZ(Swaziland)");
            countryComboBox.addItem("SE(Sweden)");
            countryComboBox.addItem("CH(Switzerland)");
            countryComboBox.addItem("SY(Syria)");
            countryComboBox.addItem("TW(Taiwan)");
            countryComboBox.addItem("TJ(Tajikistan)");
            countryComboBox.addItem("TZ(Tanzania)");
            countryComboBox.addItem("TH(Thailand)");
            countryComboBox.addItem("TG(Togo)");
            countryComboBox.addItem("TK(Tokelau)");
            countryComboBox.addItem("TO(Tonga)");
            countryComboBox.addItem("TT(Trinidad and Tobago)");
            countryComboBox.addItem("TN(Tunisia)");
            countryComboBox.addItem("TR(Turkey)");
            countryComboBox.addItem("TM(Turkmenistan)");
            countryComboBox.addItem("UG(Uganda)");
            countryComboBox.addItem("UA(Ukraine)");
            countryComboBox.addItem("US(United states)");
            countryComboBox.addItem("AE(United Arab Emirates)");
            countryComboBox.addItem("GB(United Kingdom)");
            countryComboBox.addItem("UY(Uruguay)");
            countryComboBox.addItem("UZ(Uzbekistan)");
            countryComboBox.addItem("VU(Vanuatu)");
            countryComboBox.addItem("VA(Vatican)");
            countryComboBox.addItem("VE(Venezuela)");
            countryComboBox.addItem("VN(Viet Nam)");
            countryComboBox.addItem("VG(British Virgin Islands)");
            countryComboBox.addItem("VI(U.S. Virgin Islands)");
            countryComboBox.addItem("EH(Western Sahara)");
            countryComboBox.addItem("YE(Yemen)");
            countryComboBox.addItem("YU(Yugoslavia)");
            countryComboBox.addItem("ZR(Zaire)");
            countryComboBox.addItem("ZM(Zambia)");
            countryComboBox.addItem("ZW(Zimbabwe)");
            countryComboBox.setSelectedItem("US(United states)");
            userNameText.requestFocus();		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+90,JPanel1.getPreferredSize().height+153));
		JTextArea1.setPreferredSize(new Dimension(JTextArea1.getPreferredSize().width+0,JTextArea1.getPreferredSize().height+51));


          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JPanel1= new javax.swing.JPanel();
        rmiRadioButton= new javax.swing.JRadioButton();
        sessionRadioButton= new javax.swing.JRadioButton();
        jmsRadioButton= new javax.swing.JRadioButton();
        JPanel6= new javax.swing.JPanel();
        userNameLabel= new javax.swing.JLabel();
        userNameText= new javax.swing.JTextField(10);
        passwordLabel= new javax.swing.JLabel();
        passwordField= new javax.swing.JPasswordField();
        hostLabel= new javax.swing.JLabel();
        hostText= new javax.swing.JTextField();
        tcpPortLabel= new javax.swing.JLabel();
        tcpPortText= new javax.swing.JTextField();
        rmiPortLabel= new javax.swing.JLabel();
        rmiPortText= new javax.swing.JTextField();
        languageLabel= new javax.swing.JLabel();
        languageComboBox= new javax.swing.JComboBox();
        countryLabel= new javax.swing.JLabel();
        countryComboBox= new javax.swing.JComboBox();


        //<End_initVariables>
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JTextArea1,BorderLayout.NORTH);
Top.add(JPanel1,BorderLayout.WEST);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(rmiRadioButton,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(sessionRadioButton,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(jmsRadioButton,cons);
Top.add(JPanel6,BorderLayout.CENTER);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(userNameLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,0,1,1,0.5,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(userNameText,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(passwordLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,1,1,1,0.5,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(passwordField,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(hostLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,2,1,1,0.5,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(hostText,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(tcpPortLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,3,1,1,0.5,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(tcpPortText,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(rmiPortLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,4,1,1,0.5,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(rmiPortText,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(languageLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,5,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(languageComboBox,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(countryLabel,cons);
inset = new Insets(10,10,10,10);
setConstraints(1,6,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(countryComboBox,cons);


//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections>

      sessionRadioButton_sessionRadioButton_conn sessionRadioButton_sessionRadioButton_conn1 =  new sessionRadioButton_sessionRadioButton_conn();
      sessionRadioButton.addItemListener(sessionRadioButton_sessionRadioButton_conn1);
      jmsRadioButton_jmsRadioButton_conn jmsRadioButton_jmsRadioButton_conn1 =  new jmsRadioButton_jmsRadioButton_conn();
      jmsRadioButton.addItemListener(jmsRadioButton_jmsRadioButton_conn1);
      rmiRadioButton_rmiRadioButton_conn rmiRadioButton_rmiRadioButton_conn1 =  new rmiRadioButton_rmiRadioButton_conn();
      rmiRadioButton.addItemListener(rmiRadioButton_rmiRadioButton_conn1);

      //<End_setUpConnections>
  }




  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }













  public LoginPanel()
  {
    //<Begin_LoginPanel>
    this.init();

    //<End_LoginPanel>
  }

  public LoginPanel(java.applet.Applet applet)
  {
    //<Begin_LoginPanel_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_LoginPanel_java.applet.Applet>
  }

	private void rmiSelectedAction()
	{
		rmiPortLabel.setVisible(true);
		rmiPortText.setVisible(true);
		if(applet != null)
		{
			languageLabel.setVisible(false);
			languageComboBox.setVisible(false);
			countryLabel.setVisible(false);
			countryComboBox.setVisible(false);
		}
	}

	private void sessionSelectedAction()
	{
		rmiPortLabel.setVisible(false);
		rmiPortText.setVisible(false);
		if(applet != null)
		{
			languageLabel.setVisible(false);
			languageComboBox.setVisible(false);
			countryLabel.setVisible(false);
			countryComboBox.setVisible(false);
		}
	}

	private void jmsSelectedAction()
	{
		rmiPortLabel.setVisible(false);
		rmiPortText.setVisible(false);
		if(applet != null)
		{
			languageLabel.setVisible(false);
			languageComboBox.setVisible(false);
			countryLabel.setVisible(false);
			countryComboBox.setVisible(false);
		}
	}

	public boolean areInputValuesValid()
	{
		boolean valid=true;
		if (userNameText.getText().length()<1)
		{
			valid=false;
		}
		if ((new String(passwordField.getPassword())).length()<1)
		{
			valid=false;
		}
		if (hostText.getText().length()<1)
		{
			valid=false;
		}
		if (tcpPortText.getText().length()<1)
		{
			valid=false;
		}
		if ((rmiPortText.isVisible())&&(rmiPortText.getText().length()<1))
		{
			valid=false;
		}
		return valid;
	}
	public Properties getProperties()
	{
		Properties prop=new Properties();
		prop.put("USERNAME",userNameText.getText());
		prop.put("PASSWORD",new String(passwordField.getPassword()));
		prop.put("HOSTNAME",hostText.getText().trim());
		prop.put("RMI_REG_PORT",rmiPortText.getText().trim());
		//prop.put("PROV_HOSTNAME",hostText.getText().trim());
		//prop.put("PROV_SERVERPORT",tcpPortText.getText().trim());
		prop.put("WEB-SERVER-PORT",tcpPortText.getText().trim());
		String lang=languageComboBox.getSelectedItem().toString().substring(0,2).trim();
		prop.put("LANGUAGE",lang);
		String country=countryComboBox.getSelectedItem().toString().substring(0,2).trim();
		prop.put("COUNTRY",country);
		if (sessionRadioButton.isSelected())
		{
			prop.put("CONNECTION_TYPE","SESSION");
		}
		else if (jmsRadioButton.isSelected())
		{
			prop.put("CONNECTION_TYPE","JMS");
			try
			{
			   //Modified by vkarthik for SSL
				String strProtocolName = com.adventnet.nms.util.NmsClientUtil.getProtocolName();
				//java.net.URL u= new java.net.URL("http://"+hostText.getText().trim()+":"+tcpPortText.getText().trim()+"/conf/JmsConfiguration.xml");
				java.net.URL u= new java.net.URL(strProtocolName +"://"+hostText.getText().trim()+":"+tcpPortText.getText().trim()+"/conf/JmsConfiguration.xml");
				BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
				//Modify Ends
        		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				InputSource source = new InputSource(br);
				Document doc = docBuilder.parse(source);
				Element root = doc.getDocumentElement();

				String contextFactory = root.getAttribute("CONTEXT_FACTORY");
				String provider_Url = root.getAttribute("PROVIDER_URL");
				String requestQueueName = root.getAttribute("requestQueue");
				String responseQueueName = root.getAttribute("responseQueue");
				String queueConnectionFactoryName = root.getAttribute("QueueConnectionFactory");

				if(contextFactory != null && contextFactory.trim().length() != 0)
				{
			                        prop.put("CONTEXT_FACTORY", contextFactory);
				}
				if(provider_Url != null && provider_Url.trim().length() != 0)
				{
					prop.put("PROVIDER_URL", provider_Url);
				}
				if(requestQueueName != null && requestQueueName.trim().length() != 0)
				{
					prop.put("requestQueue", requestQueueName);
				}
				if(responseQueueName != null && responseQueueName.trim().length() != 0)
				{
					prop.put("responseQueue", responseQueueName);
				}
				if(queueConnectionFactoryName != null && queueConnectionFactoryName.trim().length() != 0)
				{
					prop.put("QueueConnectionFactory", queueConnectionFactoryName);
				}

				//Get the childNodes
				NodeList list = root.getChildNodes();
				int length = list.getLength();
				for(int i=0; i<length; i++)
				{
					Node node = list.item(i);
            				if(!(node instanceof Element))
					continue;
					Element childElement = (Element)node;
					String property = childElement.getAttribute("property");
					String value = childElement.getAttribute("value");
            			            if(property != null && property.trim().length() != 0 && value != null && value.trim().length() != 0)
                        			{
						prop.put(property,value);
			                        }
				}
			}
			catch(Exception e)
			{
				System.err.println(resourceBundle.getString("Cannot able to access JMS parameters"));
			}
		}
		else
		{
			prop.put("CONNECTION_TYPE","RMI");
		}
		return prop;
	}










//<Begin__class_rmiRadioButton_rmiRadioButton_conn>

 class rmiRadioButton_rmiRadioButton_conn implements java.awt.event.ItemListener, java.io.Serializable
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
  if (rmiRadioButton.isSelected())
  {
   rmiSelectedAction();
  }
     }
}
//<End__class_rmiRadioButton_rmiRadioButton_conn>




//<Begin__class_jmsRadioButton_jmsRadioButton_conn>

 class jmsRadioButton_jmsRadioButton_conn implements java.awt.event.ItemListener, java.io.Serializable
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
  if (jmsRadioButton.isSelected())
  {
   jmsSelectedAction();
  }
     }
}
//<End__class_jmsRadioButton_jmsRadioButton_conn>
//<Begin__class_sessionRadioButton_sessionRadioButton_conn>

 class sessionRadioButton_sessionRadioButton_conn implements java.awt.event.ItemListener, java.io.Serializable
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
  if (sessionRadioButton.isSelected())
  {
   sessionSelectedAction();
  }
     }
}
//<End__class_sessionRadioButton_sessionRadioButton_conn>


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











