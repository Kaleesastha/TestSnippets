# $Id: BackupDB.sh,v 1.7 2010/11/30 08:48:44 prabakaran Exp $


#This script is used to take standalone backup of your data. This scipt will create backup file with the name BackUp_**.data under <WebNMS>/backup directory.

cd ../../

. ./setEnv.sh

MYSQL_DUMP=/Users/venkat-0773/del/nortel/backup_issue/mysql_dump
export PGPASSWORD=postgres

CLASSPATH=$MYSQL_DUMP:$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH:$DB_CLASSPATH:$TRANSACTION_CLASSPATH:$TL1_CLASSPATH:$MS_CLASSPATH:$HBN_LIB_HOME:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$NMS_CLASSES/Mail.jar:$NMS_CLASSES/json.jar:$NMS_CLASSES/AdventNetSnmp.jar

export CLASSPATH

# Usage : java jdbc.BackUpImpl [-n <number_of_records>]
# Note: the argument number_of_records denote only the number of records to be written to the file at a time. But, the database records are fetched fully in a single query only.

#$JAVA_HOME/bin/java -Dpgsql.home=$PGSQL_HOME -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -DseparateJVM=true jdbc.BackUpImpl $*
$JAVA_HOME/bin/java -Dpgsql.home=$PGSQL_HOME -Dmysql.home=$MYSQL_HOME -DenableDebug=true -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -DseparateJVM=true jdbc.MysqldumpBackup $*
