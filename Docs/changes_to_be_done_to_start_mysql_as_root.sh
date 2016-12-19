#!bin/sh

cd ..
. ./setEnv.sh

export IFS="/"

MYSQL_HOME=$NMS_HOME/mysql
DATA_DIR=$MYSQL_HOME/data

token=""

SEP="/"
for word in $DATA_DIR; do
token=$token$word/

if [ "$token" != "/" ];then
chmod a+x "$token"
fi
done
/usr/sbin/useradd mysql
/usr/bin/useradd mysql
chmod -R mysql $DATA_DIR
