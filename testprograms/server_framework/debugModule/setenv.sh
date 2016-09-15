# $Id: setenv.sh,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ 
NMS_HOME=$HOME/AdventNet/WebNMS
CLASSPATH=$NMS_HOME/classes:.:$NMS_HOME/AdventNetSNMPv3/classes
CLASSPATH=$CLASSPATH:$NMS_HOME/classes/ManagementServer.jar
CLASSPATH=$CLASSPATH:$NMS_HOME/classes/xml.jar
CLASSPATH=$CLASSPATH:$NMS_HOME/classes/ldap_jars/ldap.jar:$NMS_HOME/classes/ldap_jars/jndi.jar:$NMS_HOME/classes/ldap_jars/providerutil.jar
export CLASSPATH
