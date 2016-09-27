
#!/bin/sh
# This script should be run with JDK 1.2 or above

JAVA_HOME=../jre
export JAVA_HOME
NMSHOME="../"
DBNAME="MySQL" 

CLASSPATH=../examples/classes:../classes:../classes/crimson.jar:../classes/jaxp.jar 
export CLASSPATH

$JAVA_HOME/bin/java  com.adventnet.dbutil.GenerateTables -m linkobject.conf -o output  
$JAVA_HOME/bin/java  com.adventnet.dbutil.Prepare -m linkobject.conf -o output  
$JAVA_HOME/bin/java  com.adventnet.dbutil.GenerateClasses -m linkobject.conf -o output -p output/prepare.conf -t template.conf  
