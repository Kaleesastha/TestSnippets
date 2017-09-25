#!/bin/bash
#$Id: startnms.sh 2012-4-17 13:41:21

CWD=`pwd`
FILEPATH=`dirname $0`
cd $CWD && cd $FILEPATH && cd ../


CLASSPATH=
export CLASSPATH
DISPLAY=
export DISPLAY
CUSTOMPATH=
MOPATH=

. ./setEnv.sh 
. ./bin/gvmEnv.sh
. ./bin/gvmLog.sh

#const variable defination
#log file location
LOG_FILE_DIR="$GVM_LOG_DIR"
#lock file location
LOCK_FILE_DIR=$LOG_FILE_DIR
#the lock file which mark the GVM already running
LOCK_FILE_NAME="Gvm.pid"
#the lock file which mark the GVM backup is in progress
BACKUP_LOCK_FILE="BackupStarted.lock"
#sync the startnms operation
STARTNMS_LOCK_FILE="Gvm.lock"

trap "release_lock" EXIT TERM INT KILL HUP QUIT ABRT

# Reset the server down count files
reset_srv_down_count_file()
{
  echo "0" > $HBCountFile
  cat /dev/null > $SrvDownCountFile
}

# add CLP entry for GVMB
add_clp_entry()
{
  COMMON_OAM_BINPATH=`swami -k runtime_commonOAM_bin |grep "value:" | awk '{print $2}'`
  clp_cmd="${COMMON_OAM_BINPATH}/../apache/rcscripts/vhost.sh"
  gvmb_title="GENView Manager (GVM)"
  $clp_cmd add -a gvmb -d none
  $clp_cmd linkurl -a gvmb -u "https://${GVM_VIP}:9091" -t "${gvmb_title}"
}

wait_for_shutdown_on_mate()
{	
  count=24
  sleep_time=5
  while [ $count -gt 0 ]
  do
    mate_gvmpid=""
    mate_gvmpid=`ssh root@typhoon-base-mate ps -ef | awk '/com.adventnet.nms.startnms.NmsMainBE/ {print $2}'`
    if [ "$mate_gvmpid" != "" ]
    then
      gvmlog "[info] GVMB is running on mate, waiting for $sleep_time seconds."
      count=`expr $count - 1`
      sleep $sleep_time
    else
      break
    fi
  done
  if [ $count -gt 0 ]
  then
    return 0
  else
    gvmlog "[error] GVMB is runiing on mate, can not start GVMB on this unit."
    exit 1
  fi
}

getCLLI()
{
    if [ -f /usr/bin/commonPathTool ]
    then
        commonPath=`/usr/bin/commonPathTool`
        if [ -x "${commonPath}/bin/systemclliExe" ]
        then
            gvmlog "[info] CBM script is available"
            CM_CLLI=`${commonPath}/bin/systemclliExe`
            gvmlog "[info] CM_CLLI is ${CM_CLLI}"
        fi
    else
        commonBinPath=`swami -k runtime_commonOAM_bin | grep "value:" | awk '{print $2}'`
        if [ -x "${commonBinPath}/systemclliExe" ]
        then
            gvmlog "[info] CBM script is available"
            CM_CLLI=`${commonBinPath}/systemclliExe`
            gvmlog "[info] CM_CLLI is ${CM_CLLI}"
        fi
    fi

    if [ "${CM_CLLI}" != "" ]
    then
        CM_CLLI=`echo "${CM_CLLI}" | sed 's/ /_/g'`
    fi
}

changeOfficeName()
{
    engToNatFile=${NMS_HOME}/html/EnglishToNative.properties
    offNameChanged="success"
    getCLLI
    if [ "${CM_CLLI}" != "" ]
    then
        gvmlog "[info] Changing the Office Name to CLLI value..."
        cp -p ${engToNatFile} ${engToNatFile}.bak 2>&1 >/dev/null | gvmlog
        if [ "${PIPESTATUS[0]}" -eq 0 ]
        then
            sed -i "s/OfficeName=.*/OfficeName=${CM_CLLI}/" ${engToNatFile} 2>&1 >/dev/null | gvmlog
            if [ "${PIPESTATUS[0]}" -ne 0 ]
            then
                offNameChanged="failure"
            fi
            sed -i "s/OfficeName\\\.*/OfficeName\\\ -\\\ GVM=${CM_CLLI} - GVM/" ${engToNatFile} 2>&1 >/dev/null | gvmlog
            if [ "${PIPESTATUS[0]}" -ne 0 ]
            then
                offNameChanged="failure"
            fi
            if [ ${offNameChanged} == "failure" ]
            then
                gvmlog "[error] Unable to change the Office Name to CLLI, see gvm.log for more details."
                mv ${engToNatFile}.bak ${engToNatFile} 2>&1 >/dev/null | gvmlog
            else
                gvmlog "[info] Successfully changed the Office Name to CLLI value."
                echo "CLLI" > ${NMS_HOME}/html/officeName.conf
            fi
        else
            gvmlog "[error] Unable to take a backup of EnglishToNative.properties file..."
            gvmlog "[info] Skipping Office Name change..."
        fi
    else
        gvmlog "[error] Could not get CM_CLLI so keeping the IP for now..."
        echo "IP" > ${NMS_HOME}/html/officeName.conf
    fi
}

checkOfficeName()
{
    if [ ! -f ${NMS_HOME}/html/officeName.conf ]
    then
        touch ${NMS_HOME}/html/officeName.conf
        changeOfficeName
    else
        curOfficeName=`cat ${NMS_HOME}/html/officeName.conf | sed 's/ //g'`
        if [ ${curOfficeName} == "IP" ]
        then
            changeOfficeName
        fi
    fi
}

get_lock()
{
    if [ "$(find $GVM_DATA_DIR/$STARTNMS_LOCK_FILE -mmin +10)" != "" ]
    then
        gvmlog "[info] Detected stale lock file, deleting it and continuing"
        release_lock
    fi
    
    counter=0
    #we must call one system command which will queued by OS
    until mkfifo -m 0700 "$GVM_DATA_DIR/$STARTNMS_LOCK_FILE" >/dev/null 2>&1
    do
        gvmlog "[debug]Will sleep 5 seconds and then retesting lock file."
        sleep 5
        counter=`expr $counter + 5`
        if [ $counter -gt 600 ]  # 10 mins
        then
            gvmlog "[error] Cannot create lock file"
            exit 1
        fi
    done
}

release_lock()
{
    gvmlog "[debug]Removing lock file:$GVM_DATA_DIR/$STARTNMS_LOCK_FILE."
	rm -f "$GVM_DATA_DIR/$STARTNMS_LOCK_FILE" > /dev/null 2>&1
	if [ $? -ne 0 ]; then
		mv -f "$GVM_DATA_DIR/$STARTNMS_LOCK_FILE" /tmp > /dev/null 2>&1
        #try once again
        rm -f /tmp/$STARTNMS_LOCK_FILE > /dev/null 2>&1
	fi
    gvmlog "[debug]Removed lock file:$GVM_DATA_DIR/$STARTNMS_LOCK_FILE."
}

checkIsBackupInProgress()
{
        if [ -f "$GVM_DATA_DIR/$BACKUP_LOCK_FILE" ]
        then
                # Check and see if the backup is actually running locally
                bupid=`ps -ef | grep java | grep "com.genband.gvm.rmi.RunBackup" | grep -v "grep" | awk {'print $2'}`
                if [ "$bupid" != "" ]
                then
                    gvmlog "GVM local backup in progress."
                    exit 1
                fi

                # Check and see if the backup is actually running on the mate
                mate_bupid=`ssh root@typhoon-base-mate ps -ef | awk '/com.genband.gvm.rmi.RunBackup/ {print $2}'`
                if [ "$mate_bupid" != "" ]
                then
                    gvmlog "GVM remote backup in progress."
                    exit 1
                fi

                # Check and see if the backup DB is actually running locally
                budbpid=`ps -ef | grep java | grep "jdbc.BackUpImpl" | grep -v "grep" | awk {'print $2'}`
                if [ "$budbpid" != "" ]
                then
                    gvmlog "GVM local backupDB in progress."
                    exit 1
                fi

                # Check and see if the backup DB is actually running on the mate
                mate_budbpid=`ssh root@typhoon-base-mate ps -ef | awk '/jdbc.BackUpImpl/ {print $2}'`
                if [ "$mate_budbpid" != "" ]
                then
                    gvmlog "GVM remote backupDB in progress."
                    exit 1
                fi

               # else, delete dead lock file and continue
               gvmlog "Detected dead backup lock file, deleting $GVM_DATA_DIR/$BACKUP_LOCK_FILE"
               rm -f "$GVM_DATA_DIR/$BACKUP_LOCK_FILE" > /dev/null 2>&1
        fi
}

checkUser()
{
	uid=`id -u`
	if [ $uid -ne 0 ]
	then
		gvmlog "root user privileges required to start gvm server."
		exit 1
	fi
}

checkIsAlreadyRunning()
{
	nmspid=`ps -ef | grep java | grep "com.adventnet.nms.startnms.NmsMainBE" | grep -v "grep" | awk {'print $2'}`
	if [ -f "$LOCK_FILE_DIR/$LOCK_FILE_NAME" ]
	then
		if [ "$nmspid" = "" ]; then
			rm -rf "$LOCK_FILE_DIR/$LOCK_FILE_NAME" > /dev/null 2>&1
			if [ $? -ne 0 ]; then
				mv -f "$LOCK_FILE_DIR/$LOCK_FILE_NAME" /tmp > /dev/null 2>&1
			fi
			return 0
		else
			pid=`cat "$LOCK_FILE_DIR/$LOCK_FILE_NAME"`
			if [ "$pid" != "$nmspid" ]; then
				rm -rf "$LOCK_FILE_DIR/$LOCK_FILE_NAME" > /dev/null 2>&1
				if [ $? -ne 0 ]; then
					mv -f "$LOCK_FILE_DIR/$LOCK_FILE_NAME" /tmp > /dev/null 2>&1
				fi
			fi
			gvmlog "The GVM server is already running."
			exit 0
		fi
	else
		if [ "$nmspid" = "" ]; then
			return 0
		else
			gvmlog "The GVM server is already running,but without flag file."
			exit 0
		fi
	fi
}

checkLogDir()
{
	if [ ! -e "$NMS_HOME/logs" ]
	then
		if [ ! -d "$LOG_FILE_DIR" ];then
			gvmlog "error" "The GVM applog dir does not exist."
			exit 1
		else
			if [ ! -d  "$LOG_FILE_DIR/logs" ]; then
				mkdir "$LOG_FILE_DIR/logs" > /dev/null 2>&1
			fi
	    	ln -s "$LOG_FILE_DIR/logs"  "$NMS_HOME/logs" > /dev/null 2>&1
		fi
	fi

	if [ ! -e "$TOMCAT_HOME/logs" ]
	then
		if [ ! -d "$LOG_FILE_DIR" ];then
			gvmlog "error" "The GVM applog dir does not exist."
			exit 1
		else
			if [ ! -d  "$LOG_FILE_DIR/tomcat/logs" ]; then
				mkdir -p "$LOG_FILE_DIR/tomcat/logs" > /dev/null 2>&1
			fi
	    	ln -s "$LOG_FILE_DIR/tomcat/logs"  "$TOMCAT_HOME/logs" > /dev/null 2>&1
		fi
	fi
	gvmlog "checkLogDir done"
}

performRestore()
{
	if [ -d "$GVM_DATA_DIR/backup" ]; then
		gvmlog "Will do the RestoreDB....."
		/bin/bash $NMS_HOME/bin/backup/RestoreDB.sh "$GVM_DATA_DIR/backup" 
	fi

	if [ -f $NMS_HOME/conf/Polling.conf.bak ]; then
		cp -f $NMS_HOME/conf/Polling.conf.bak $NMS_HOME/conf/Polling.conf > /dev/null 2>&1
	fi

	gvmlog "performRestore done."
}

genConfigFiles()
{
	if [ -f $TOMCAT_HOME/conf/backup/server.xml ]; then
		cp -f $TOMCAT_HOME/conf/backup/server.xml  $TOMCAT_HOME/conf > /dev/null 2>&1
	fi

	if [ -f $WEBSERVER_HOME/conf/backup/workers.properties ]; then
		cp -f $WEBSERVER_HOME/conf/backup/workers.properties $WEBSERVER_HOME/conf > /dev/null 2>&1
	fi

	gvmlog "genConfigFiles done."
}

setPaths()
{
CUSTOMPATH=$NMS_CLASSES/securityinterfaces.jar:$NMS_CLASSES/g9ems_if.jar:$NMS_CLASSES/SSPFS.jar:$NMS_CLASSES/OMGNotification.jar:$NMS_CLASSES/jaas.jar:$NMS_CLASSES/HTTPClient.jar:$NMS_CLASSES/CemAdapter.jar:$NMS_CLASSES/cpr-utils.jar:$NMS_CLASSES/log4j.jar:$NMS_HOME/resources:$NMS_CLASSES/OMIClient.jar:$NMS_CLASSES/axis.jar:$NMS_CLASSES/axis-ant.jar:$NMS_CLASSES/jaxrpc.jar:$NMS_CLASSES/saaj.jar:$NMS_CLASSES/wsdl4j-1.5.1.jar:$NMS_CLASSES/commons-discovery-0.2.jar:$NMS_CLASSES/jsch-0.1.28.jar:$NMS_CLASSES/gvmTool.jar:$GENBAND_CLASSPATH:$CEM_CLASSPATH:$IEMS_CLASSPATH
OOM_DEBUG=$NMS_CLASSES/oomDebug_5200.jar
CLASS_PATH=$OOM_DEBUG:$NMS_SERVER_CLASSES:$NMS_CLASSES:$NMS_CLASSES/GenbandServerClasses.jar:$MFT_CLASSPATH:$SUM_CLIENT_CLASSPATH:$SUM_SERVER_CLASSPATH:$CUSTOMPATH:$SNMP_CLASSPATH:$NBAGENT_FOR_CEM_CLASSPATH:$SAS_CLASSPATH:$XML_CLASSPATH:$OTHER_CLASSPATH:$MS_CLASSPATH:$WEBSERVER_CLASSPATH:$DB_CLASSPATH:$JAVA_CLASSPATH:$TFTP_CLASSPATH:$CLI_CLASSPATH:$TRANSACTION_CLASSPATH:$NMS_CLASSES/AdventNetSnmpDistributedAPI.jar:$NMS_CLASSES/AdventNetOPExtn.jar:$JIMI_CLASSPATH:$MOPATH:$LICENSE_PATH:$FTP_CLASSPATH:$NMS_CLASSES/AdventNetUI.jar:$NMS_HOME/mindterm/mindterm.jar:$NMS_HOME/mindterm/ntext.jar:$HBN_CLASSPATH:$HBN_LIB_CLASSPATH:$SSH3_CLASSPATH:$JSON_CLASSPATH:$TL1AGENT_CLASSPATH:$NMS_CLASSES/jdom-1.1.2.jar

CLASS_PATH=$CLASS_PATH:$TL1_CLASSPATH

CLASS_PATH=$CLASS_PATH:$JMX_AGENT_CLASSPATH

gvmlog "setPaths done"
}

createCertificates()
{
    /bin/bash $NMS_HOME/bin/createCertificates.sh
    if [ $? -ne 0 ]; then
        gvmlog "error" "Importing the certificate/key failed."
        exit 1
    fi
    gvmlog "Create certificates successufully."
}

repairTomcatLink()
{
    if [ ! -L $TOMCAT_HOME/conf ]; then
	ln -snf $GVM_DATA_DIR/tomcat/conf $TOMCAT_HOME/conf >>$GVM_LOG_DIR/gvm.log 2>&1
	gvmlog "Link repaired."
    fi
}

updateGvmPatchVersion()
{
    /bin/bash $NMS_HOME/bin/update_gvm_patch_info.sh
    if [ $? -ne 0 ]; then
        gvmlog "error" "Update gvm load version failed."
        exit 1
    fi
    gvmlog "Update gvm load version successfully."
}
databaseAvailable()
{
 port=`grep oamdb00 /etc/services | awk {'print $2'} | cut -d'/' -f1`
 if [ -z $port ]; then
   port="5433"
 fi
 oamdbpasswd=""
 oamdbpasswd=`/usr/local/bin/oamdb/oamdb_pwd_retrieve`
 if [ $? != 0 ]
 then
   oamdbpasswd="oamdb"
 fi
 PGUSER="oamdb"; export PGUSER
 PGPASSWORD=$oamdbpasswd; export PGPASSWORD
 su postgres -c "psql -hoamdb00 -p$port -t -c\"select version();\"" 2>&1
 if [ $? -ne 0 ];then
    gvmlog "[error] database is not available."
    exit 1
 fi
 gvmlog "Database is available."
}
migration()
{
    /bin/bash $NMS_HOME/bin/migrationTool.sh
    if [ $? -ne 0 ]; then
        gvmlog "error" "Migration failed, check  migrationLog.txt."
        exit 1
    fi
    gvmlog "Migration passed."
}

###start from here
get_lock
checkUser

if [ -r /opt/data/gvm/USM_UPGRADE_START ]
then
  gvmlog "INFO: Perform GVMB upgrade/downgrade."
  $NMS_HOME/bin/gvmUpgradeTasks.sh
  if [ $? -ne 0 ]
  then
    gvmlog "ERR: GVMB upgrade/downgrade failed"
    exit 1
  fi
fi

checkIsAlreadyRunning
checkIsBackupInProgress
checkLogDir
add_clp_entry
wait_for_shutdown_on_mate

# Check the perf drbd fs first to prevent use the ram fs for the gvmb perf data.
$NMS_HOME/bin/gvm_take_drbd.sh
if [ $? -eq 0 ]
then
  $NMS_HOME/bin/create_perf_data_dirs.sh
else
  gvmlog "[error] The DRBS FS /opt/data/gvm_perf is not mounted. Start WebNMS without the perf data fs."
fi

performRestore
setPaths
updateGvmPatchVersion
databaseAvailable
migration
repairTomcatLink
createCertificates
checkOfficeName

reset_srv_down_count_file

if [ ! -f $NMS_HOME/html/initiated.flag ]
then
  gvmlog "Initing config files ..."
  $JAVA_HOME/bin/java -cp $NMS_CLASSES/GenbandServerClasses.jar -Xmx128m com.nortel.iems.server.standalone.InitConfFiles -file $NMS_HOME/html/EnglishToNative.properties -token "OfficeName\=GENBAND"="OfficeName\=$GVM_VIP" "OfficeName\ -\ GVM\=GENBAND - GVM"="OfficeName\ -\ GVM\=$GVM_VIP - GVM">> $NMS_HOME/logs/startServerLog.txt 2>&1
  if [ $? -ne 0 ]; then
    gvmlog "error" "Init the config files failed."
    exit 1
  else
    touch $NMS_HOME/html/initiated.flag
  fi
  gvmlog "config files init done."
fi

if [ ! -f $WEBSERVER_HOME/conf/initiated.flag ]
then
  gvmlog "Initing Web server ..."
  genConfigFiles
  $JAVA_HOME/bin/java -cp $CLASS_PATH -Xmx128m -Dtomcatshutdown.port=$TOMCAT_SHUTDOWNPORT -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebnms.rootdir=$NMS_HOME -Dwebcontainer.home=$TOMCAT_HOME -Dwebserver.port=$WEBSERVER_PORT -Dssl.port=$SSL_PORT -Dwebcontainer.port=$WEBCONTAINER_PORT com.adventnet.nms.util.InitWebSvr $TOMCAT_HOME/conf/server.xml $WEBSERVER_HOME/conf/workers.properties >> $NMS_HOME/logs/startServerLog.txt 2>&1
  if [ $? -ne 0 ]; then
    gvmlog "error" "Init the webserver failed."
    exit 1
  else 
    touch $WEBSERVER_HOME/conf/initiated.flag
  fi
  gvmlog "Web server init done."
fi

TOMCATLOG="-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file=$TOMCAT_HOME/conf/logging.properties"

C3P0LOG="-Dcom.mchange.v2.log.MLog=com.mchange.v2.log.FallbackMLog -Dcom.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL=INFO"

  if [ -f $NMS_HOME/logs/startServerLog.txt ]; then
     cat $NMS_HOME/logs/startServerLog.txt >> $NMS_HOME/logs/startServerLog.txt.old
  fi

#Workaround for ABP-2199
gvmlog "[info] Clearup ARP cache for ${GVM_VIP}"
ip neigh flush ${GVM_VIP}

$NCGLNICE $NICE_GVM_CPU_OPTS -- nohup $JAVA_HOME/bin/java -Dflag.classname=com.adventnet.nms.startnms.NmsMainBE -cp $CLASS_PATH -Dcom.sun.CORBA.ORBServerHost=$GVM_VIP -Dcatalina.home=$TOMCAT_HOME -Dcatalina.base=$TOMCAT_HOME -Dnms.server.host=gvm -Dnortel.server.host=gvm -Dpgsql.home=${PGSQL_HOME} -Dfqdn.name=gvm -Dssl.port=$SSL_PORT -Dwebserver.port=$SSL_PORT -Dresource_check="$TOMCAT_SHUTDOWNPORT" -Dhttps.protocols="TLSv1.2,TLSv1.1,TLSv1" -Dwebserver.rootdir=$WEBSERVER_HOME -Dwebcontainer.port=$SSL_PORT -Djavax.net.ssl.trustStore=conf/server.truststore -Xmx2048m -Xms128m -XX:PermSize=64m -XX:MaxPermSize=512m -XX:HeapDumpPath=./logs -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysActAsServerClassMachine -XX:+CMSIncrementalMode -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -Dhttp.port=$WEBSERVER_PORT -DCRYPTO_CLASS=com.nortel.iems.common.IEMSCryptoGraphAPIImpl -Dcom.sun.management.jmxremote.port=16000 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.snmp.interface=0.0.0.0 -Dcom.sun.management.snmp.acl=false -Dcom.sun.management.snmp.port=16500 $TOMCATLOG $C3P0LOG -DConnectionCustomizer=test.ConnectionCustomizerImpl -Djava.awt.headless=true -Dprint.timestamp=true com.adventnet.nms.startnms.NmsMainBE  NMS_BE_PORT 9095 ROOT_DIR $NMS_HOME SERVICE TRUE > $NMS_HOME/logs/startServerLog.txt 2>&1 &

gvmlog "Starting GVM daemon...."
#set timeout monittoring for WebNMS processes start. Among these processes the 
#MLRunProcess is one which takes longest time to start,  up to 300 seconds. 
#Thus set monitoring time up to 300+120 seconds here
count=42
sleep_time=10
while [ $count -gt 0 ]
do
 fgrep 'GVM Server modules started successfully' $NMS_HOME/logs/startServerLog.txt 2>&1 
 if [ $? -ne 0 ]
 then 
    gvmlog "[info] waiting for GVM start to complete...."
    count=`expr $count - 1`
    sleep $sleep_time
 else
    break
 fi
done
if [ $count -eq 0 ]
then
  gvmlog "[error] GVM start not complete in specfied time"
  exit 1
else
  gvmlog "[info] GVM start is complete"
  exit 0
fi
