#setup-service.sh
# $Id: setup-service_linux.sh,v 1.1 2007/07/13 20:00:08 mallikarjuna Exp $
INSTOPT=$1
CURRENTDIR=`pwd`
ABSDIRPATH=`dirname $0`

printusage()
{
    echo "Usage: $0 [ argument ]
    install   - to install the EMS service so that it 
                gets started automatically during system startup.
                Operation can be performed only by SuperUser.

    uninstall - to uninstall a previously installed EMS service from
                the system and the EMS service will no longer be 
                started during system startup. Operation can be 
                performed only by SuperUser."
    exit
}
cd ../..

. ./setEnv.sh


if [ "X$INSTOPT" = "X" ]
then 
printusage
fi

ABSDIRPATH=$NMS_HOME
INIT_DIR=/etc/init.d
rootuser="false"
APP_NAME="testwrapper"

user_id=`id | awk '$1 ~ /uid=0/ {print "1"}'`
if [ x"${user_id}" != "x1" ]; then
 echo " "
 echo "install/uninstall operations can be performed only by SuperUser"
 echo " "
 exit 1;
fi

doinstall()
{
    dir=$ABSDIRPATH
    if [ -f $INIT_DIR/$APP_NAME ]
    then
        echo "EMS Service already installed !"
    else
        cd /etc/init.d
        ln -sf $dir/$APP_NAME $INIT_DIR/$APP_NAME
        chmod a+x $INIT_DIR/$APP_NAME
        chmod a+x $dir/$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc2.d/S20$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc3.d/S20$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc4.d/S20$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc5.d/S20$APP_NAME
    
        ln -sf $INIT_DIR/$APP_NAME /etc/rc0.d/K20$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc1.d/K20$APP_NAME
        ln -sf $INIT_DIR/$APP_NAME /etc/rc6.d/K20$APP_NAME

        chkconfig --add $APP_NAME
        echo "EMS Service installed successfully !"
    fi  
}

douninstall()
{
    dir=$ABSDIRPATH
    if [ -f $INIT_DIR/$APP_NAME ]
    then
            chkconfig --del $APP_NAME

        rm -f $INIT_DIR/$APP_NAME
        rm -f /etc/rc2.d/S20$APP_NAME
        rm -f /etc/rc3.d/S20$APP_NAME
        rm -f /etc/rc4.d/S20$APP_NAME
        rm -f /etc/rc5.d/S20$APP_NAME

        rm -f /etc/rc0.d/K20$APP_NAME
        rm -f /etc/rc1.d/K20$APP_NAME
        rm -f /etc/rc6.d/K20$APP_NAME
        echo "EMS Service uninstalled successfully !"
    else
        echo "EMS Service not installed currently"
    fi  
}

if [ "${INSTOPT}" = "uninstall" ]
  then
    douninstall
  else
if [ "${INSTOPT}" = "install" ]
  then
    doinstall
  else
  printusage
  fi  
 fi

