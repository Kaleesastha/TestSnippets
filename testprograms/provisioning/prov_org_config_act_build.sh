# This unzips the prov_org_config_act_Validate.zip and combines the prov_org_config_act_orgconfigacttester.java and then zips back into prov_org_config_act_Validate_<date of generation>
d=`date +_%b_%d_%y`

unzip prov_org_config_act_Validate.zip 

cp prov_org_config_act_tester.java prov_org_config_act_Mastertestoutput.txt prov_org_config_act_Testinput.txt after_startnms.bat after_startnms.sh before_startnms.bat before_startnms.sh prov_org_config_act_Validate/

zip -rq prov_org_config_act_Validate$d.zip prov_org_config_act_Validate/*

rm -rf prov_org_config_act_Validate

