//$Id: ManagedObjectGeneratorPlugin.java,v 1.2 2007/02/22 15:03:06 srajeswari Exp $
package com.adventnet.builder.source.plugin;

import java.util.*;

import com.adventnet.builder.utils.general.Assorted;
import com.adventnet.builder.xmlparser.ParseHolder;
import com.adventnet.builder.framework.utils.constants.SourceGenerationConstants;
import com.adventnet.builder.source.sourceengine.*;

import com.adventnet.builder.framework.utils.general.InteractionEditorHelper;

public class ManagedObjectGeneratorPlugin extends AbstractSourceGeneratorPlugin implements SourceGenerationConstants
{
    ArrayList arrListName=new ArrayList();
    ArrayList arrListDataType=new ArrayList();
    String extendsTag;
    private boolean superNeeded = false;
    ArrayList primitiveDataArr = new ArrayList();
    ArrayList timeDateArrList = new ArrayList();
    
    public ManagedObjectGeneratorPlugin()
    {
        
    }
    
    
    public void addCode() throws Exception
    {
        fillDataTypeArrList();
        Vector classVec = sgInfo.getVector("CLASS");
        Vector propertyVec = sgInfo.getVector("MOPROPERTY");
        if(classVec == null)
        {
            return;
        }
        Hashtable classHt = (Hashtable)classVec.firstElement();
        addClassCode(sourceWrapper,classHt);
		if(propertyVec!=null) {
	        for(Enumeration enumerate=propertyVec.elements();enumerate.hasMoreElements();)
    	    {
        	    Hashtable moPropHt = (Hashtable)enumerate.nextElement();
            	addMethodCode(sourceWrapper,moPropHt);
	        }
    	    addGetPropertiesMethod(sourceWrapper);
        	addSetPropertiesMethod(sourceWrapper);
		}
        addExtraMethods(sourceWrapper);
    }

    private void fillDataTypeArrList()
    {
        primitiveDataArr.add("int");
        primitiveDataArr.add("float");
        primitiveDataArr.add("long");
        primitiveDataArr.add("byte");
        primitiveDataArr.add("boolean");
        primitiveDataArr.add("double");
        timeDateArrList.add("Date");
        timeDateArrList.add("Time");
        timeDateArrList.add("Timestamp");
    }

    public void addClassCode(JavaSourceWrapper sourceWrapper,Hashtable classHt)
    {
        String accessModifier = ParseHolder.getString("accessModifier",classHt,"public ");
        String classModifier = ParseHolder.getString("classModifier",classHt,"");
        String className = ParseHolder.getString("name",classHt,"MyManagedObject");
        String pkgName = ParseHolder.getString("package",classHt,"com.adventnet.nms.topodb");
        extendsTag = ParseHolder.getString("extends",classHt,"");
        String implementsTag = ParseHolder.getString("implements",classHt,"");
        String importTag = ParseHolder.getString("import",classHt,"com.adventnet.nms.topodb.*");
        String[] importList = Assorted.singleStrToArr(importTag);
        String[] implementsArr = Assorted.singleStrToArr(implementsTag);
        sourceWrapper.setAccessSpecifier(accessModifier+ " " +classModifier);
        sourceWrapper.setClassName(className);
        if((extendsTag!=null) && !(extendsTag.equals("")))
        {
            sourceWrapper.setExtendsClass(extendsTag);
        }
	if(implementsArr != null)
	{
            StringBuffer interfaceMethodsBuffer = new StringBuffer();
            if(InteractionEditorHelper.generateInterfaceImplementations(implementsArr,interfaceMethodsBuffer))
            {
                sourceWrapper.addCode("interfaces",interfaceMethodsBuffer.toString());
                for(int i=0;i<implementsArr.length;i++)
                {
                    sourceWrapper.addImplements(implementsArr[i]);
                }
            }

	}
	if(importList != null)
	{
            for(int i=0;i<importList.length;i++)
            {
                sourceWrapper.addImports(importList[i]);
            }
	}
        sourceWrapper.setPackageName(pkgName);
    }

    public void addGetPropertiesMethod(JavaSourceWrapper sourceWrapper)
    {
        String variableName="";
        String dataType;
        String getMethodHeader="\n"+"  "+"public" +" "+"java.util.Properties"+" "+"getProperties()";
        StringBuffer dataBuffer1=new StringBuffer();
        
        if(extendsTag==null||extendsTag.equals(""))
        {
            dataBuffer1.append("\tjava.util.Properties mosource_prop = new java.util.Properties();");
        }
        else
        {
            dataBuffer1.append("\tjava.util.Properties mosource_prop;");
            dataBuffer1.append("\n\tmosource_prop = super.getProperties();\n");
        }
        String getMethodId="getProperties";
        for(int i=0;i<arrListName.size();i++)
        {
            variableName=(String) arrListName.get(i);
            dataType=(String)arrListDataType.get(i);
            getPropertiesMethodSource(dataType,variableName,dataBuffer1);
        }
        dataBuffer1.append("\n\treturn mosource_prop;");
        sourceWrapper.addMethod(getMethodId,getMethodHeader,dataBuffer1.toString(),dataBuffer1+variableName,null);
    }
    
    private void getPropertiesMethodSource(String dataType,String variableName,StringBuffer dataBuffer1)
    {
        String methodName = getMethodName(variableName);
        if(dataType.equals("String"))
        {
			dataBuffer1.append("\n\tif(get"+methodName+"()!=null)"   );
			dataBuffer1.append("\n\t{"   );
            dataBuffer1.append("\n\tmosource_prop.put(\""+variableName+"\"," +"get"+methodName+"());");
			dataBuffer1.append("\n\t}"   );
        }
		else if(    dataType.equals("Date") || dataType.equals("Time") || dataType.equals("Timestamp")) 
        {
			dataBuffer1.append("\n\tif(get"+methodName+"()!=null)"   );
			dataBuffer1.append("\n\t{"   );
            dataBuffer1.append("\n\t\tmosource_prop.put(\""+variableName+"\","+"String.valueOf("+"get"+methodName+"()));");
			dataBuffer1.append("\n\t}"   );
        }
		else
        {
            dataBuffer1.append("\n\tmosource_prop.put(\""+variableName+"\","+"String.valueOf("+"get"+methodName+"()));");
        }

    }

    public void addSetPropertiesMethod(JavaSourceWrapper sourceWrapper)
    {
        String variableName;
        String dataType;
        String getMethodHeader="\n  public" +" "+"void"+" "+"setProperties("+"java.util.Properties p)";
        String getMethodId="setProperties";
        for(int i=0;i<arrListName.size();i++)
        {
            variableName= (String)arrListName.get(i);
            dataType=(String)arrListDataType.get(i);
            if((extendsTag!=null) && !(extendsTag.equals("")) && i==(arrListName.size()-1))
            {
                superNeeded = true;
            }
            sourceWrapper.addMethod(getMethodId,getMethodHeader,setPropertiesMethodSource(dataType,variableName),dataType+variableName,null);
        }
    }

    public void addExtraMethods(JavaSourceWrapper sourceWrapper)
    {
        sourceWrapper.addMethod("clone","\n  public Object clone()","\treturn super.clone();");
        sourceWrapper.addMethod("checkStatus","\n  public int checkStatus() throws java.rmi.RemoteException","\treturn super.checkStatus();");
    }
   
    private String setPropertiesMethodSource(String dataType,String variableName)
    {
        StringBuffer dataBuffer2 = new StringBuffer();
        dataBuffer2.append("\n\tString"+" "+variableName+"value="+"p.getProperty(\""+variableName+"\");");
        dataBuffer2.append("\n\t\tif("+variableName+"value"+"!=null)");
        dataBuffer2.append("\n\t"+OPENBRACES);
        
        if(dataType.equals("String"))
        {
            dataBuffer2.append("\t\t"+variableName+"="+variableName+"value;");
        }
        else if(timeDateArrList.contains(dataType))
        {
            dataBuffer2.append("\t\t"+variableName+"="+dataType+".valueOf("+variableName+"value"+");");
        }
        else if(dataType.equals("boolean"))
        {
            //dataBuffer2.append("\t\t"+variableName+"=Boolean.getBoolean("+variableName+"value);");
		      dataBuffer2.append("\t\t"+variableName+"= Boolean.valueOf("+variableName+"value).booleanValue();");
        }
        else if(dataType.equals("int"))
        {
            dataBuffer2.append("\t\t"+variableName+"=Integer.parseInt("+variableName+"value);");
        }          
        else if(dataType.equals("long"))
        {
            dataBuffer2.append("\t\t"+variableName+"=Long.parseLong("+variableName+"value);");
        }
        else if(dataType.equals("float"))
        {
            dataBuffer2.append("\t\t"+variableName+"=Float.parseFloat("+variableName+"value);");
        }
        else if(dataType.equals("double"))
        {
            dataBuffer2.append("\t\t"+variableName+"=Double.parseDouble("+variableName+"value);");
        }
        dataBuffer2.append("\n\t\tp.remove(\""+variableName+"\");");
        dataBuffer2.append("\n\t"+CLOSEBRACES);

        if(superNeeded)
        {
            dataBuffer2.append("\tsuper.setProperties("+" p);\n");
        }
        return dataBuffer2.toString();
    }

    public void addMethodCode(JavaSourceWrapper sourceWrapper,Hashtable methodHt)
    {
        String variableName = ParseHolder.getString("name",methodHt,null);
        arrListName.add(variableName);
        String dataType= ParseHolder.getString("dataType",methodHt,"String");
        arrListDataType.add(dataType);
        String defaultValue= ParseHolder.getString("defaultValue",methodHt,"");
        String accessModifier= ParseHolder.getString("accessModifier",methodHt,"public ");
        String fieldModifier = ParseHolder.getString("fieldModifier",methodHt,"");
        
        //System.out.println("variableName:"+variableName); 
        if(variableName == null)
        {
            return;
        }
        sourceWrapper.addVariables(variableName,getVariableName(dataType,variableName,defaultValue,accessModifier),getClassName(dataType));
        String methodName = null;
        if(Character.isLowerCase(variableName.charAt(0)))
        {
            Character firstLetter = new Character(Character.toUpperCase(variableName.charAt(0)));
            methodName = firstLetter.toString()+variableName.substring(1);
        }
         
        String setMethodId = "set" + methodName +"_"+dataType;
        String setMethodHeader ="\n"+"  "+ "public"+"  "+fieldModifier +"void set"+methodName+"("+dataType+" " +variableName+"Arg)";
        sourceWrapper.addMethod(setMethodId,setMethodHeader,getSetMethodSource(dataType,variableName),"SETMETHOD_"+variableName,null);
                
        String getMethodId ="get" + methodName;
        String getMethodHeader ="\n"+"  "+"public"+"  "+fieldModifier +dataType+ " get"+methodName+"()";
        sourceWrapper.addMethod(getMethodId,getMethodHeader,getGetMethodSource(dataType,variableName),"GETMETHOD_"+variableName,null);
    }

    private String getVariableName(String dataType,String variableName,String defaultValue,String access)
    {
        boolean defaultValueBool = defaultValueValidation(defaultValue);
        String variable = null;
        if(!primitiveDataArr.contains(dataType))
        {
            if(!defaultValue.equals(""))
            {
                if(defaultValueBool)
                {
                    defaultValue = "null";
                }
                else
                {
                    if(timeDateArrList.contains(dataType))
                    {
                        defaultValue = dataType+".valueOf("+"\""+defaultValue+"\""+")";
                    }
                    else
                    {
                        defaultValue = "\""+defaultValue+"\"";
                    }
                }
            }
        }
        if(!access.equals("package") && !access.equals(""))
        {
            variable = "\t" + access + " " +dataType+" "+ variableName;
        }
        else
        {
            variable = "\t" +dataType+" "+ variableName;
        }
        if(defaultValue.equals(""))
        {
            return variable;
        }
        else
        {
            variable = variable+" = "+defaultValue;
        }
        return variable;
    }

    private boolean defaultValueValidation(String defaultValueArg)
    {
        boolean bool = false;
        if(defaultValueArg.equals("null"))
        {
            bool = true;
        }
        else
        {
            bool = false;
        }
        return bool;
    }

    private String getSetMethodSource(String dataType,String variableName)
    {
        StringBuffer dataBuffer = new StringBuffer();
        if(dataType.indexOf("String")!= -1)
        {
            dataBuffer.append("    "+"if("+variableName+"Arg != null)");
            dataBuffer.append("\n"+"    "+"{");
            dataBuffer.append("\n\t"+variableName+" = "+variableName+"Arg;");
            dataBuffer.append("\n"+"    "+"}");
        }
        else 
        {
            dataBuffer.append("    " + variableName+" = "+variableName+"Arg;");
        }
        
        return dataBuffer.toString();
    }

    private String getGetMethodSource(String dataType,String variableName)
    {
        return "\treturn " + variableName+";";
    }

    private String getClassName(String dataType)
    {
        if(dataType.indexOf("String")!= -1)
        {
            return "java.lang.String";
        }
        else
        {
            return dataType;
        }
    }

    private String getMethodName(String variableName)
    {
        String methodName = variableName;
        if(Character.isLowerCase(variableName.charAt(0)))
        {
            Character firstLetter = new Character(Character.toUpperCase(variableName.charAt(0)));
            methodName =  firstLetter.toString()+variableName.substring(1);
        }
        return methodName;
    }
}
