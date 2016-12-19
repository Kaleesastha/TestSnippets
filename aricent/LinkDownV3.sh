#!/bin/sh

#$Id: LinkDownV3.sh,v 1.2 2007/07/27 15:13:21 venkatramanan Exp $
cd ..
. ./setEnv.sh

# Description: This file generates snmpv3 link down trap to the localhost with user name test
# Usage: snmpv3trap [-d] [-p port] [-e engineID(0x....)] [-a auth_protocol] [-w auth_password] [-s priv_password] [-n contextName] [-i contextID] userName host TimeTicksvalue OIDvalue [OID {INTEGER | STRING | GAUGE | TIMETICKS | OPAQUE | IPADDRESS | COUNTER | COUNTER64 | UNSIGNED32} value] ...

CLASSPATH=$NMS_CLASSES:$NMS_SERVER_CLASSES:$SNMP_CLASSPATH:$NMS_HOME/genTrap

$JAVA_HOME/bin/java -cp $CLASSPATH snmpv3trap -p 162 -e 0x3139322e3136382e342e31363535303031 test localhost 0 .1.3.6.1.6.3.1.1.5.3 2.2.1.1.5 INTEGER 2 2.2.1.7.5 INTEGER 1656032 2.2.1.8.5 INTEGER 16759200
cd $NMS_HOME/genTrap
