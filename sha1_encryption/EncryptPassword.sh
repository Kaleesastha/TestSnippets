#!/bin/sh
#$Id: EncryptPassword.sh,v 1.2 2007/06/20 11:33:17 venkatramanan Exp $
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
CLASS_PATH=$NMS_SERVER_CLASSES:$NMS_CLASSES:$XML_CLASSPATH

$JAVA_HOME/bin/java -cp $CLASS_PATH -DONEWAY_ENCRYPTION=true -DPASSWORD_ONEWAY_ENCRYPTION=com.adventnet.security.authentication.PasswordEncryptionImpl com.adventnet.nms.util.EncryptPassword $NMS_HOME $UserName $Password $EncryptPassword
#=============================   END   ==================================#
