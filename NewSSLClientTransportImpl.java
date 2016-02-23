package com.adventnet.management.transport;
import  javax.net.ssl.*;
import  com.sun.net.ssl.*;
import  java.applet.Applet;
import  java.io.*;
import com.adventnet.management.transport.*;

public class NewSSLClientTransportImpl extends SSLClientTransportImpl
{
    public NewSSLClientTransportImpl()
    {
    	super();
      System.setProperty("javax.net.ssl.trustStore", System.getProperty("user.dir")+File.separator+"Truststore.truststore");
    }
}
