#$Id: before_startnms.sh,v 1.1 2006/02/18 06:23:46 sasidhar Exp $
# This script may need to be modified for your environment before starting the WebNMS server.



cp -rf testsetup/* ../

cd ..

rm -rf test13 test14
rm -rf test15/* test16/*

cd prov_org_config_act_Validate
