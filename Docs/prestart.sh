#!bin/sh
 
cd ..
. ./setEnv.sh
 
 
MYSQL_HOME=$NMS_HOME/mysql
DATA_DIR=$MYSQL_HOME/data
#echo $MYSQL_HOME
#echo $NMS_HOME
token=""

export IFS="/"
for word in $DATA_DIR; do
token=$token$word/
#echo "$token"
 
if [ "$token" != "/" ];then
chmod a+x "$token"
fi
done
export IFS=

#echo "$DATA_DIR"
#Uncomment either of the below two lines based on your environment
#/usr/sbin/useradd mysql
#/usr/bin/useradd mysql

chown -R mysql "$DATA_DIR" 
