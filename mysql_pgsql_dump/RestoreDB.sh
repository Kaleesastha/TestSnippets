
#  $Id: RestoreDB.sh,v 1.6 2010/11/30 08:51:46 prabakaran Exp $


#This script is used restore the backup data. You can also give just only file name [no need to give full path] of backup_file as an argument. It will automatically takes the path <Web Nms Home>/backup.
if [ $# -le 0 ]
then
echo "Usage : RestoreDB.sh <backup_file>"
exit 1
fi

cd ../../

. ./setEnv.sh


MYSQL_DUMP=/Users/venkat-0773/del/nortel/backup_issue/mysql_dump
export PGPASSWORD=postgres
CLASSPATH=$MYSQL_DUMP:$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH:$DB_CLASSPATH:$HBN_LIB_HOME:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$TRANSACTION_CLASSPATH:$SNMP_CLASSPATH:$NMS_CLASSES/Mail.jar:$NMS_CLASSES/json.jar:$MS_CLASSPATH:$TL1_CLASSPATH
export CLASSPATH


opt_c=
echo ""
echo "WARNING! Attempting to restore the data !!! This will result in loosing your current data !!! Do you want to continue [y/n] ?" $opt_c
read opt
if [ "$opt" != "y" -a "$opt" != "Y" ]
then
exit
fi

#$JAVA_HOME/bin/java -Dpgsql.home=$PGSQL_HOME -Dwebnms.rootdir=$NMS_HOME -DbackupHome=$NMS_HOME/bin/backup jdbc.RestoreBackup FILE $1
$JAVA_HOME/bin/java  -Dpgsql.home=$PGSQL_HOME -Dmysql.home=$MYSQL_HOME -Dwebnms.rootdir=$NMS_HOME -DbackupHome=$NMS_HOME/bin/backup jdbc.MysqldumpRestore FILE $1
