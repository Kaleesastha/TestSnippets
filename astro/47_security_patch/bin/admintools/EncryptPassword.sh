#!/bin/sh
#$Id: EncryptPassword.sh,v 1.2.2.1 2004/04/21 05:41:06 rajagopal Exp $

###  This script is used to encrypt the password .

usage()
{
echo Usage : EncryptPassword username password EncryptPassword
echo UserName : UserName 
echo Password  : Password of the User
echo EncryptPassword  : The password to be encrypted.
exit 1
}

if [ $# -lt 3 ]
then
  usage
fi

cd ../..
. ./setEnv.sh

UserName=$1
Password=$2
EncryptPassword=$3

VULENRABILITY_PATCH=/home/test/venkat/webnms/8043_tomcat_with_47_sp4/security/security_debug2.jar

CLASS_PATH=$VULENRABILITY_PATCH:$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH


$JAVA_HOME/bin/java -cp $CLASS_PATH com.adventnet.nms.util.EncryptPassword $NMS_HOME $UserName $Password $EncryptPassword


#=============================   END   ==================================#

