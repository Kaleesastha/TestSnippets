// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransportXMLReader.java

package com.adventnet.management.mstransport;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class TransportXMLReader
{

    public TransportXMLReader(InputStream inputstream, String s)
    {
        serverclassname = null;
        clientclassname = null;
        args = null;
        try
        {
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.parse(inputstream);
            Element element = document.getDocumentElement();
            getTransportDescriptors(element, s);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void getTransportDescriptors(Object obj, String s)
    {
        Vector vector = new Vector();
        NodeList nodelist = ((Node)obj).getChildNodes();
        int i = nodelist.getLength();
        for(int j = 0; j < i; j++)
        {
            Node node = nodelist.item(j);
            if(node.getNodeType() == 1 && node.getNodeName().equals("PROTOCOL"))
            {
                Properties properties = loadTransportAttributes((Element)node);
                String s1 = (String)properties.get("NAME");
                if(s1.equals(s))
                {
                    serverclassname = (String)properties.get("SERVER_CLASS_NAME");
                    clientclassname = (String)properties.get("CLIENT_CLASS_NAME");
                    NodeList nodelist1 = node.getChildNodes();
                    int k = nodelist1.getLength();
                    for(int l = 0; l < k; l++)
                    {
                        Node node1 = nodelist1.item(l);
                        if(node1.getNodeType() == 1 && node1.getNodeName().equals("ARG"))
                        {
                            NodeList nodelist2 = node1.getChildNodes();
                            Node node2 = nodelist2.item(0);
                            if(node2 != null)
                                vector.addElement(node2.getNodeValue());
                        }
                    }

                    if(vector.size() != 0)
                        args = giveMeObjectArray(vector);
                }
            }
        }

    }

    public static Properties loadTransportAttributes(Element element)
    {
        Properties properties = new Properties();
        NamedNodeMap namednodemap = element.getAttributes();
        for(int i = 0; i < namednodemap.getLength(); i++)
            properties.put(namednodemap.item(i).getNodeName(), namednodemap.item(i).getNodeValue());

        return properties;
    }

    public String getServerClassName()
    {
        return serverclassname;
    }

    public String getClientClassName()
    {
        return clientclassname;
    }

    public Object[] getArguments()
    {
        return args;
    }

    public static Object[] giveMeObjectArray(Vector vector)
    {
        int i = vector.size();
        Object aobj[] = new Object[i];
        for(int j = 0; j < i; j++)
            aobj[j] = vector.elementAt(j);

        return aobj;
    }

    public static void getTransportProvider(String as[])
        throws Exception
    {
        String s = as.length <= 0 ? "TCP" : as[0];
        String s1 = "conf/mstransportProvider.conf";
        FileInputStream fileinputstream = new FileInputStream(s1);
        TransportXMLReader transportxmlreader = new TransportXMLReader(fileinputstream, s);
        Object aobj[] = transportxmlreader.getArguments();
    }

    public static void main(String args1[])
        throws Exception
    {
        String s = args1.length <= 0 ? "TCP" : args1[0];
        String s1 = "conf/mstransportProvider.conf";
        FileInputStream fileinputstream = new FileInputStream(s1);
        TransportXMLReader transportxmlreader = new TransportXMLReader(fileinputstream, s);
        System.out.println(transportxmlreader.getServerClassName());
        System.out.println(transportxmlreader.getClientClassName());
        Object aobj[] = transportxmlreader.getArguments();
        for(int i = 0; i < aobj.length; i++)
            System.out.println(aobj[i]);

    }

    private String serverclassname;
    private String clientclassname;
    private Object args[];
}
