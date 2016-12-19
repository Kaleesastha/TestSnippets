SELECT 'DROPPING ALL FOREIGN KEYS' AS 'MIGRATION PROCESS STATUS ... ';
SET FOREIGN_KEY_CHECKS=0;
SET GLOBAL FOREIGN_KEY_CHECKS=0;

alter table WebNmsDB.ALERTUSERPROPS DROP FOREIGN KEY FKCFB5A6A93E26DDB;
alter table WebNmsDB.AttributeValueChangeEvent DROP FOREIGN KEY FK28B9493539AF4EAA;
alter table WebNmsDB.AvailabilityEvent DROP FOREIGN KEY FKD2C5CDDF8ABECD54;
alter table WebNmsDB.BlackBoxDataConfig DROP FOREIGN KEY FK9B73E218E3070E50;
alter table WebNmsDB.BlackBoxLogStream DROP FOREIGN KEY FK4336FD8286709EC;
alter table WebNmsDB.CNEOMIManagementEvent DROP FOREIGN KEY FK7A783126CD9C739B;
alter table WebNmsDB.CORBANode DROP FOREIGN KEY FK427DD3C7435BA95;
alter table WebNmsDB.CRITERIAPROPERTIES DROP FOREIGN KEY FK435EDDD2977A5201;
alter table WebNmsDB.CnpACL DROP FOREIGN KEY FK7896B1056453B274;
alter table WebNmsDB.CnpAirFilter DROP FOREIGN KEY FK2A94459D2D974A15;
alter table WebNmsDB.CnpCage DROP FOREIGN KEY FK9A40CCA1793783C3;
alter table WebNmsDB.CnpCageInfo DROP FOREIGN KEY FKBDFA6CEF89FA8F3A;
alter table WebNmsDB.CnpClusterManager DROP FOREIGN KEY FK83EF00F824E9DDA;
alter table WebNmsDB.CnpClusterManagerPG DROP FOREIGN KEY FK4432ACEFE9043491;
alter table WebNmsDB.CnpEmsSensor DROP FOREIGN KEY FK660878A0690B7D18;
alter table WebNmsDB.CnpEmsServer DROP FOREIGN KEY FK660A5429690D58A1;
alter table WebNmsDB.CnpEmsServerPG DROP FOREIGN KEY FKCC5F7E09AFF5998;
alter table WebNmsDB.CnpEthernetPort DROP FOREIGN KEY FKE27A36DDAE8BA228;
alter table WebNmsDB.CnpEthernetPortGroup DROP FOREIGN KEY FK3CB491A2E1241391;
alter table WebNmsDB.CnpExtSwitch DROP FOREIGN KEY FK129704F0159A0968;
alter table WebNmsDB.CnpExternalServer DROP FOREIGN KEY FK102F3937F629075;
alter table WebNmsDB.CnpFan DROP FOREIGN KEY FK7896C78E1CE0D446;
alter table WebNmsDB.CnpFileSystemInfo DROP FOREIGN KEY FK89CE7ABED717DA89;
alter table WebNmsDB.CnpFruInfo DROP FOREIGN KEY FK573430724D7648E1;
alter table WebNmsDB.CnpLocationInfo DROP FOREIGN KEY FKF15933E8BD6A9F33;
alter table WebNmsDB.CnpMonDevInfo DROP FOREIGN KEY FK55548DC11BB9FA7;
alter table WebNmsDB.CnpPEM DROP FOREIGN KEY FK7896E9931CE0F64B;
alter table WebNmsDB.CnpPSBlackBoxDC DROP FOREIGN KEY FKAC4BAEF3CA37A655;
alter table WebNmsDB.CnpPSBlackBoxLS DROP FOREIGN KEY FKAC4BAFFBBA405086;
alter table WebNmsDB.CnpPSCoreFileMgmtLS DROP FOREIGN KEY FKBEC113CBA5DDDD56;
alter table WebNmsDB.CnpPayloadServer DROP FOREIGN KEY FK3C27392CD5B95AE4;
alter table WebNmsDB.CnpPayloadServerPG DROP FOREIGN KEY FKCF3DA8238CB3D71B;
alter table WebNmsDB.CnpRTM DROP FOREIGN KEY FK7896F2E61CE0FF9E;
alter table WebNmsDB.CnpRaid DROP FOREIGN KEY FK9A479E6F793E5591;
alter table WebNmsDB.CnpRaidController DROP FOREIGN KEY FKFFD3DF6B7E337C4D;
alter table WebNmsDB.CnpRaidFan DROP FOREIGN KEY FKB45F2A24F3B5815C;
alter table WebNmsDB.CnpRaidLogicalDrive DROP FOREIGN KEY FK95AEEF103A8076B2;
alter table WebNmsDB.CnpRaidLogicalUnit DROP FOREIGN KEY FK80BABB3EC84DE776;
alter table WebNmsDB.CnpRaidPEM DROP FOREIGN KEY FKB45F4C29F3B5A361;
alter table WebNmsDB.CnpRaidPhysicalDrive DROP FOREIGN KEY FKEC8874C4DBE70E3C;
alter table WebNmsDB.CnpRaidPort DROP FOREIGN KEY FKD78ADB90A38AFDDB;
alter table WebNmsDB.CnpRaidPortGroup DROP FOREIGN KEY FKFDDDE4CFADF90FBE;
alter table WebNmsDB.CnpServerInfo DROP FOREIGN KEY FKE8A581B6F50BD881;
alter table WebNmsDB.CnpShelfEEPROM DROP FOREIGN KEY FK5A777C25E8B0DDDD;
alter table WebNmsDB.CnpShmm DROP FOREIGN KEY FK9A482D9A793EE4BC;
alter table WebNmsDB.CnpShmmBMC DROP FOREIGN KEY FKF573B57E34CA0CB6;
alter table WebNmsDB.CnpSwitch DROP FOREIGN KEY FK29C4E4D965CFF6BB;
alter table WebNmsDB.CnpSwitchUnit DROP FOREIGN KEY FK40CD1DFD9829D55F;
alter table WebNmsDB.CnpSystemAlarmPanel DROP FOREIGN KEY FK65012D279D2B4C9;
alter table WebNmsDB.CnpSystemInfo DROP FOREIGN KEY FK7BCC6EA28832C56D;
alter table WebNmsDB.CnpTermServer DROP FOREIGN KEY FKE17B31F438D7E956;
alter table WebNmsDB.ConnectivityData DROP FOREIGN KEY FK8BE5E215D4EFF6;
alter table WebNmsDB.Constituent DROP FOREIGN KEY FKF55AC104856D278;
alter table WebNmsDB.CoreFileMgmtLogStream DROP FOREIGN KEY FK4F535E08BA15349C;
alter table WebNmsDB.DataObject DROP FOREIGN KEY FKBB1A73A9A7754CCC;
alter table WebNmsDB.EMHAlert DROP FOREIGN KEY FK6794709C196074E5;
alter table WebNmsDB.EMHEvent DROP FOREIGN KEY FK67D159DA66FF875B;
alter table WebNmsDB.EMHInfoEvent DROP FOREIGN KEY FK4FC121CCD07CAC4D;
alter table WebNmsDB.EVENTUSERPROPS DROP FOREIGN KEY FK54B8432B87584B8F;
alter table WebNmsDB.Endpoint DROP FOREIGN KEY FK6BA181B5D3F52B8A;
alter table WebNmsDB.EventControl DROP FOREIGN KEY FK78A59D83F9945526;
alter table WebNmsDB.HapNeControl DROP FOREIGN KEY FK39603EEF7A94456C;
alter table WebNmsDB.Interface DROP FOREIGN KEY FK95678D1931884ABE;
alter table WebNmsDB.InterfaceContainer DROP FOREIGN KEY FK3BE68B688D85973D;
alter table WebNmsDB.IpAddress DROP FOREIGN KEY FKD8D77CAD7825E164;
alter table WebNmsDB.IpConfig DROP FOREIGN KEY FK3C9427693BF5820C;
alter table WebNmsDB.LogStream DROP FOREIGN KEY FKEBCF7BC44C2A0887;
alter table WebNmsDB.LogStreamDataConfig DROP FOREIGN KEY FK3D63EA709F587B3;
alter table WebNmsDB.LogicalContainer DROP FOREIGN KEY FK73B48E9863A233B;
alter table WebNmsDB.LogicalElement DROP FOREIGN KEY FK1C5DFAD35C62F0F6;
alter table WebNmsDB.LogicalUnit DROP FOREIGN KEY FK834A650D1C49DD24;
alter table WebNmsDB.LogicalUnitStream DROP FOREIGN KEY FK7D15E2ED4DD089B0;
alter table WebNmsDB.MAPPEDPROPERTIES DROP FOREIGN KEY FK4D2F47A6977A5201;
alter table WebNmsDB.MAPUSERPROPS DROP FOREIGN KEY FK30B70EA9AF738122;
alter table WebNmsDB.ManagedGroupObject DROP FOREIGN KEY FK2D38159FD43F4EA2;
alter table WebNmsDB.ManagedObject DROP FOREIGN KEY FKB855B9E41BE4C5D;
alter table WebNmsDB.MapContainer DROP FOREIGN KEY FKFA2B62A5334AE2DB;
alter table WebNmsDB.MapGroup DROP FOREIGN KEY FKD33BEA36F4BC0D9;
alter table WebNmsDB.MapLink DROP FOREIGN KEY FK951453561311A184;
alter table WebNmsDB.NECF_ATTR DROP FOREIGN KEYCRTFK_ATTR;
alter table WebNmsDB.NECF_ATTR DROP FOREIGN KEYIPPARAMFK_ATTR;
alter table WebNmsDB.NECF_ATTR DROP FOREIGN KEYOPPARAMFK_ATTR;
alter table WebNmsDB.NECF_ATTR DROP FOREIGN KEYRELFK_ATTR;
alter table WebNmsDB.NECF_ATTR DROP FOREIGN KEYSETFK_ATTR;
alter table WebNmsDB.NECF_CRT DROP FOREIGN KEYNECFFK_NECFCRT;
alter table WebNmsDB.NECF_DEL DROP FOREIGN KEYNECFFK_DEL;
alter table WebNmsDB.NECF_IPPARAM DROP FOREIGN KEYOPFK_IPPARAM;
alter table WebNmsDB.NECF_MOD DROP FOREIGN KEYNECFFK_MOD;
alter table WebNmsDB.NECF_OP DROP FOREIGN KEYNECFFK_OP;
alter table WebNmsDB.NECF_OPPARAM DROP FOREIGN KEYOPFK_OPPARAM;
alter table WebNmsDB.NECF_REL DROP FOREIGN KEYNECFFK_REL;
alter table WebNmsDB.NECF_SET DROP FOREIGN KEYMODFK_SET;
alter table WebNmsDB.Network DROP FOREIGN KEY FKD119F20E5044AD45;
alter table WebNmsDB.NetworkElementManagement DROP FOREIGN KEY FK7D44D3F1C014A294;
alter table WebNmsDB.Node DROP FOREIGN KEY FK2522223D370DA5;
alter table WebNmsDB.ObjectCreationDeletionEvent DROP FOREIGN KEY FK69B254AE150BABA3;
alter table WebNmsDB.POLLUSERPROPS DROP FOREIGN KEY FK5DBA4B86D7D7502;
alter table WebNmsDB.PhysicalContainer DROP FOREIGN KEY FK6DB5CD4A25E1F9E1;
alter table WebNmsDB.PhysicalElement DROP FOREIGN KEY FK92A33E054D3C391C;
alter table WebNmsDB.PhysicalEntity DROP FOREIGN KEY FKB2493FBAF24E35DD;
alter table WebNmsDB.PhysicalSubUnit DROP FOREIGN KEY FK86610AED40FA0604;
alter table WebNmsDB.PhysicalUnit DROP FOREIGN KEY FKB1E53F1B32D3F6BE;
alter table WebNmsDB.PhysicalUnitStream DROP FOREIGN KEY FKC33C7A7B3D5D8F2;
alter table WebNmsDB.PortObject DROP FOREIGN KEY FK679DDF409E30C459;
alter table WebNmsDB.Printer DROP FOREIGN KEY FK50765FFA21D93CDB;
alter table WebNmsDB.ProtectionGroup DROP FOREIGN KEY FK41498306FBE27E1D;
alter table WebNmsDB.RelationObject DROP FOREIGN KEY FKEFA3E87B2FA8DE9E;
alter table WebNmsDB.RelationshipChangeEvent DROP FOREIGN KEY FKF35DB352E4A0747;
alter table WebNmsDB.SBNE DROP FOREIGN KEY FK26BC468EC439E9;
alter table WebNmsDB.SnmpInterface DROP FOREIGN KEY FK7DB9517B6E19E932;
alter table WebNmsDB.SnmpNode DROP FOREIGN KEY FK293EA880896A8103;
alter table WebNmsDB.StateChangeEvent DROP FOREIGN KEY FK8A948F991CE6F71A;
alter table WebNmsDB.SwitchObject DROP FOREIGN KEY FK4C0B63B3695F01CC;
alter table WebNmsDB.TL1Interface DROP FOREIGN KEY FK11A58880F6B1DA18;
alter table WebNmsDB.TL1Node DROP FOREIGN KEY FKE013625BB76D185D;
alter table WebNmsDB.TOPOUSERPROPS DROP FOREIGN KEY FK483583EBFBE5F355;
alter table WebNmsDB.TopoObject DROP FOREIGN KEY FK608221B9A4FF80BC;
alter table WebNmsDB.UserDefinedEvent DROP FOREIGN KEY FKB4D6AD9C4729151D;

SELECT 'Creating PollFilters table' AS 'MIGRATION PROCESS STATUS ... ';
create table PollFilters(CLASSNAME varchar(100) NOT NULL,PRIMARY KEY(CLASSNAME));

SELECT 'alter PolledData table' AS 'MIGRATION PROCESS STATUS ... ';
alter table PolledData drop primary key;
alter table PolledData add column OWNERNAME varchar(25) default 'NULL' after FAILURETHRESHOLD;
alter table PolledData add UNIQUE KEY(`NAME`,`AGENT`,`OID`);
alter table PolledData drop column DISCRIMINATOR;

SELECT 'create table DBPOLL' AS 'MIGRATION PROCESS STATUS ... ';
create table DBPOLL (KEYSTRING VARCHAR(250) NOT NULL, VALUESTRING varchar(250), PRIMARY KEY (KEYSTRING),index DBPOLL0_ndx (KEYSTRING));
alter table PolledData add column ACTIVE_STR varchar(10) default NULL after period ;
update PolledData set ACTIVE_STR = 'true' where ACTIVE is true;
update PolledData set ACTIVE_STR = 'false' where ACTIVE is false;
alter table PolledData drop column ACTIVE;
alter table PolledData change ACTIVE_STR ACTIVE varchar(10);

alter table PolledData add column LOGDIRECTLY_STR varchar(10) default NULL AFTER ACTIVE;
update PolledData set LOGDIRECTLY_STR = 'true' where LOGDIRECTLY is true;
update PolledData set LOGDIRECTLY_STR = 'false' where LOGDIRECTLY is false;
alter table PolledData drop column LOGDIRECTLY;
alter table PolledData change LOGDIRECTLY_STR LOGDIRECTLY varchar(10);
alter table PolledData add column SAVEDATA_STR varchar(10) default NULL AFTER LOGFILE;
update PolledData set SAVEDATA_STR = 'true' where SAVEDATA is true;
update PolledData set SAVEDATA_STR = 'false' where SAVEDATA is false;
alter table PolledData drop column SAVEDATA;
alter table PolledData change SAVEDATA_STR SSAVE varchar(10);

alter table PolledData change column THRESHOLD THRESHOLD_STR varchar(10) default NULL;
alter table PolledData add column THRESHOLD bit(1) default NULL AFTER SSAVE;
update PolledData set THRESHOLD = THRESHOLD_STR like 'true';
alter table PolledData drop column THRESHOLD_STR;
alter table PolledData add column ISMULTIPLEPOLLEDDATA_STR varchar(10) default NULL AFTER THRESHOLD;
update PolledData set ISMULTIPLEPOLLEDDATA_STR = 'true' where ISMULTIPLEPOLLEDDATA is true;
update PolledData set ISMULTIPLEPOLLEDDATA_STR = 'false' where ISMULTIPLEPOLLEDDATA is false;
alter table PolledData drop column ISMULTIPLEPOLLEDDATA;
alter table PolledData change ISMULTIPLEPOLLEDDATA_STR ISMULTIPLEPOLLEDDATA varchar(10);
alter table PolledData add column SAVEABSOLUTES_STR varchar(10) default NULL AFTER NUMERICTYPE;
update PolledData set SAVEABSOLUTES_STR = 'true' where SAVEABSOLUTES is true;
update PolledData set SAVEABSOLUTES_STR = 'false' where SAVEABSOLUTES is false;
alter table PolledData drop column SAVEABSOLUTES;
alter table PolledData change SAVEABSOLUTES_STR SAVEABSOLUTES varchar(10);
alter table PolledData add column TIMEAVG_STR varchar(10) default NULL AFTER THRESHOLD;
update PolledData set TIMEAVG_STR = 'true' where TIMEAVG is true;
update PolledData set TIMEAVG_STR = 'false' where TIMEAVG is false;
alter table PolledData drop column TIMEAVG;
alter table PolledData change TIMEAVG_STR TIMEAVG varchar(10);
alter table PolledData add column SAVEONTHRESHOLD_STR varchar(10) default NULL AFTER CURRENTSAVECOUNT;
update PolledData set SAVEONTHRESHOLD_STR = 'true' where SAVEONTHRESHOLD is true;
update PolledData set SAVEONTHRESHOLD_STR = 'false' where SAVEONTHRESHOLD is false;
alter table PolledData drop column SAVEONTHRESHOLD;
alter table PolledData change SAVEONTHRESHOLD_STR SAVEONTHRESHOLD varchar(10);
create index PolledData0_ndx ON PolledData (NAME);
create index PolledData1_ndx ON PolledData (AGENT);
create index PolledData2_ndx ON PolledData (OID);
create index PolledData3_ndx ON PolledData (ID);
create index PolledData4_ndx ON PolledData (COMMUNITY);
create index PolledData5_ndx ON PolledData (PERIOD);
create index PolledData6_ndx ON PolledData (TIMEVAL);
create index PolledData7_ndx ON PolledData (OWNERNAME);
drop index PARENTOBJ_ndx on PolledData;

SELECT 'create table POLLUSERPROPS' AS 'MIGRATION PROCESS STATUS ... ';
drop table POLLUSERPROPS;
create table POLLUSERPROPS (NAME varchar(100) NOT NULL, AGENT varchar(100) NOT NULL, OID varchar(200) NOT NULL, OWNERNAME varchar(25) NOT NULL, PROPNAME varchar(150) NOT NULL, PROPVAL varchar(150));
create index POLLUSERPROPS0_ndx on POLLUSERPROPS (NAME);
create index POLLUSERPROPS1_ndx on POLLUSERPROPS (AGENT);
create index POLLUSERPROPS2_ndx on POLLUSERPROPS (OID);
create index POLLUSERPROPS3_ndx on POLLUSERPROPS (OWNERNAME);

SELECT 'alter Alert table' AS 'MIGRATION PROCESS STATUS ... ';
alter table Alert add column OWNERNAME varchar(100) NOT NULL after WEBNMS;
alter table Alert add column MAPNAME varchar(100) default NULL after SOURCE;
alter table Alert add column STAGE INTEGER default 0 after WHO;
alter table Alert add column PRIORITY INTEGER default 0 after OWNERNAME;
update Alert set OWNERNAME='NULL' where OWNERNAME is null;
create index Alert0_ndx on Alert (ENTITY);

SELECT 'create table DBALERT' AS 'MIGRATION PROCESS STATUS ... ';
create table DBALERT (KEYSTRING VARCHAR(250) NOT NULL,VALUESTRING varchar(250),PRIMARY KEY (KEYSTRING));
create index DBALERT0_ndx on DBALERT(KEYSTRING);
update DBALERT set VALUESTRING = (select DISCRIMINATOR from Alert where ENTITY=KEYSTRING);
alter table Alert drop column DISCRIMINATOR;

SELECT 'alter ALERTUSERPROPS table' AS 'MIGRATION PROCESS STATUS ... ';
alter table ALERTUSERPROPS add column OWNERNAME VARCHAR(25) NOT NULL;
update ALERTUSERPROPS set OWNERNAME='NULL' where OWNERNAME is null;
alter table ALERTUSERPROPS add column `NAME` varchar(100) NOT NULL first;
update ALERTUSERPROPS set NAME = ENTITY;
alter table ALERTUSERPROPS drop column ENTITY;
alter table ALERTUSERPROPS drop primary key;
create index ALERTUSERPROPS0_ndx ON ALERTUSERPROPS (NAME);
create index ALERTUSERPROPS1_ndx ON ALERTUSERPROPS (OWNERNAME);

SELECT 'alter ANNOTATION table' AS 'MIGRATION PROCESS STATUS ... ';
alter table ANNOTATION add column OWNERNAME varchar(25);
alter table ANNOTATION drop column AAID;
alter table ANNOTATION drop column DISCRIMINATOR;
ALTER TABLE ANNOTATION add index ANNOTATION0_ndx (ENTITY);

SELECT 'create table DBMAP' AS 'MIGRATION PROCESS STATUS ... ';
create table DBMAP (KEYSTRING VARCHAR(250) NOT NULL, VALUESTRING varchar(250), PRIMARY KEY (KEYSTRING));
create index DBMAP0_ndx on DBMAP (KEYSTRING);
insert into DBMAP values ('Failed_Objects_Map.netmap','MapDB');
alter table MapDB add column OWNERNAME varchar(25) NOT NULL after NAME;

alter table MapDB change column ANCHORED ANCHORED varchar(10);
update MapDB set ANCHORED='true' where ANCHORED like '1';
update MapDB set ANCHORED='false' where ANCHORED like '0';

alter table MapDB change column AUTOPLACEMENT AUTOPLACEMENT varchar(10);
update MapDB set AUTOPLACEMENT='true' where AUTOPLACEMENT like '1';
update MapDB set AUTOPLACEMENT='false' where AUTOPLACEMENT like '0';

SELECT 'create table CUSTOMMAPS' AS 'MIGRATION PROCESS STATUS ... ';
create table  CUSTOMMAPS (VALUESTRING varchar(250));
insert into CUSTOMMAPS(VALUESTRING) (select Name from MapDB where Name like '%Failed%');

SELECT 'create table DEFAULTMAPS' AS 'MIGRATION PROCESS STATUS ... ';
create table  DEFAULTMAPS (VALUESTRING varchar(250));
insert into DEFAULTMAPS(VALUESTRING) (select Name from MapDB where Name NOT like '%Failed%');

SELECT 'alter MapDB table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MapDB drop column TABPANELS;
alter table MapDB drop column `TYPE`;
create index MapDB0_ndx ON MapDB (NAME);

SELECT 'alter MapSymbol table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MapSymbol add column OWNERNAME varchar(25) NOT NULL AFTER NAME;

alter table MapSymbol add column ANCHORED_STR varchar(10) default NULL AFTER PARENTNAME;
update MapSymbol set ANCHORED_STR = 'true' where ANCHORED is true;
update MapSymbol set ANCHORED_STR = 'false' where ANCHORED is false;
alter table MapSymbol drop column ANCHORED;
alter table MapSymbol change ANCHORED_STR ANCHORED varchar(10);

alter table MapSymbol add column WIDTH_STR varchar(25) default NULL after MENUNAME;
update MapSymbol set WIDTH_STR=WIDTH;
alter table MapSymbol drop column WIDTH;
alter table MapSymbol change WIDTH_STR WIDTH varchar(25);
alter table MapSymbol add column HEIGHT_STR varchar(25) default NULL after WIDTH;
update MapSymbol set HEIGHT_STR=HEIGHT;
alter table MapSymbol drop column HEIGHT;
alter table MapSymbol change HEIGHT_STR HEIGHT varchar(25);
alter table MapSymbol add column X_STR varchar(25) default NULL after HEIGHT;
update MapSymbol set X_STR=X;
alter table MapSymbol drop column X;
alter table MapSymbol change X_STR X varchar(25);
alter table MapSymbol add column Y_STR varchar(25) default NULL after X;
update MapSymbol set Y_STR=Y;
alter table MapSymbol drop column Y;
alter table MapSymbol change Y_STR Y varchar(25);

create index MapSymbol0_ndx ON MapSymbol (NAME);
create index MapSymbol1_ndx ON MapSymbol (MAPNAME);
create index MapSymbol2_ndx ON MapSymbol (OWNERNAME);
create index MapSymbol3_ndx ON MapSymbol (OBJNAME,OWNERNAME);
alter table MapSymbol drop column DISCRIMINATOR;
alter table MapSymbol drop column MAPWIDTH;
alter table MapSymbol drop column MAPHEIGHT;

SELECT 'alter MapContainer table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MapContainer add column OWNERNAME varchar(25) NOT NULL AFTER MAPNAME;
alter table MapContainer add column OBJNAME varchar(100) NOT NULL after NAME;
alter table MapContainer add column LABEL varchar(200) after OWNERNAME;
alter table MapContainer add column ICONNAME varchar(100) after LABEL;
alter table MapContainer add column MENUNAME  varchar(100) after ICONNAME;
alter table MapContainer add column WIDTH INTEGER after MENUNAME;
alter table MapContainer add column HEIGHT INTEGER after WIDTH;
alter table MapContainer add column X INTEGER after HEIGHT;
alter table MapContainer add column Y INTEGER after X;
alter table MapContainer add column WEBNMS varchar(100) after Y;
alter table MapContainer add column GROUPNAME varchar(100) after WEBNMS;
alter table MapContainer add column ANCHORED varchar(10) after GROUPNAME;
alter table MapContainer add column OBJTYPE INTEGER after ANCHORED;
alter table MapContainer add column PARENTNAME varchar(100) after OBJTYPE;

alter table MapContainer add column CONTAINMENT_STR varchar(10);
update MapContainer set CONTAINMENT_STR='true' where CONTAINMENT is true;
update MapContainer set CONTAINMENT_STR='false' where CONTAINMENT is false;
alter table MapContainer drop column CONTAINMENT;
alter table MapContainer change CONTAINMENT_STR CONTAINMENT varchar(10);

create index MapContainer0_ndx on MapContainer(MAPNAME);
create index MapContainer1_ndx on MapContainer(NAME);
create index MapContainer2_ndx on MapContainer(OWNERNAME);
create index MapContainer3_ndx on MapContainer(OBJNAME,OWNERNAME);

SELECT 'alter MapLink table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MapLink add column OWNERNAME varchar(25) AFTER MAPNAME;
alter table MapLink add column LABEL varchar(100) after LINKTYPE;
alter table MapLink add column MENUNAME varchar(100) after LABEL;
alter table MapLink add column X varchar(25) after NX;
alter table MapLink add column Y varchar(25) after NY;
alter table MapLink add column OBJNAME varchar(100) after Y;
alter table MapLink add column WEBNMS varchar(100) after STATUS;
alter table MapLink add column GROUPNAME varchar(100) after WEBNMS;
alter table Maplink change column NX NX varchar(25);
alter table MapLink change column NY NY varchar(25);
alter table MapLink change column NY NY varchar(25);
alter table MapLink change column STATUS STATUS varchar(25);

create index MapLink0_ndx on MapLink(NAME);
create index MapLink1_ndx on MapLink(MAPNAME);
create index MapLink2_ndx on MapLink(OWNERNAME);
create index MapLink3_ndx on MapLink(OBJNAME,OWNERNAME);

SELECT 'alter MapGroup table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MapGroup add column OBJNAME varchar(100) not NULL after NAME;
alter table MapGroup add column OWNERNAME varchar(25) not NULL after NAME;
alter table MapGroup add column LABEL varchar(200) not NULL after OWNERNAME;
alter table MapGroup add column ICONNAME varchar(100) after LABEL;
alter table MapGroup add column MENUNAME varchar(100) after ICONNAME;
alter table MapGroup add column WIDTH INTEGER after MENUNAME;
alter table MapGroup add column HEIGHT INTEGER after WIDTH;
alter table MapGroup add column X INTEGER after HEIGHT;
alter table MapGroup add column Y INTEGER after X;
alter table MapGroup add column WEBNMS varchar(100) after Y;
alter table MapGroup add column GROUPNAME varchar(100) after WEBNMS;
alter table MapGroup add column OBJTYPE INTEGER after GROUPNAME;
alter table MapGroup add column ANCHORED varchar(10) after OBJTYPE;

create index MapGroup0_ndx on MapGroup(MAPNAME);
create index MapGroup1_ndx on MapGroup(NAME);
create index MapGroup2_ndx on MapGroup(OWNERNAME);
create index MapGroup3_ndx on MapGroup(OBJNAME,OWNERNAME);

SELECT 'RENAME CRITERIAPROPERTIES table to CUSTOMPROPS' AS 'MIGRATION PROCESS STATUS ... ';
alter table CRITERIAPROPERTIES RENAME TO CUSTOMPROPS;
alter table CUSTOMPROPS CHANGE NAME KEYSTRING varchar(250) NOT NULL;
alter table CUSTOMPROPS CHANGE PROPNAME PROPKEY varchar(255) NOT NULL;
alter table CUSTOMPROPS CHANGE PROPVAL PROPVALUE varchar(250);
alter table CUSTOMPROPS drop primary key;
create index CUSTOMPROPS0_ndx on CUSTOMPROPS(KEYSTRING);

drop table if exists CRITERIAPROPERTIES;

SELECT 'alter MAPUSERPROPS table' AS 'MIGRATION PROCESS STATUS ... ';
alter table MAPUSERPROPS add column OWNERNAME varchar(25) default 'NULL' after MAPNAME;
alter table MAPUSERPROPS drop primary key;
create index MAPUSERPROPS0_ndx on MAPUSERPROPS(NAME);
create index MAPUSERPROPS1_ndx on MAPUSERPROPS(MAPNAME);
create index MAPUSERPROPS2_ndx on MAPUSERPROPS(OWNERNAME);

SELECT 'create table TOPODBSPECIALKEY' AS 'MIGRATION PROCESS STATUS ... ';
create table  TOPODBSPECIALKEY (KEYSTRING VARCHAR(250) NOT NULL, VALUESTRING varchar(250), PRIMARY KEY(KEYSTRING), index TOPODBSPECIALKEY0_ndx (KEYSTRING));

SELECT 'create table DBINTERFACES' AS 'MIGRATION PROCESS STATUS ... ';
create table DBINTERFACES (VALUESTRING varchar(250));

SELECT 'alter table ManagedObject  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table ManagedObject add column OWNERNAME varchar(25) not NULL after NAME;
alter table ManagedObject change column STATUSPOLLENABLED STATUSPOLLENABLED varchar(10);
update ManagedObject set STATUSPOLLENABLED='true' where STATUSPOLLENABLED like '1';
update ManagedObject set STATUSPOLLENABLED='false' where STATUSPOLLENABLED like '0';
alter table ManagedObject change column ISCONTAINER ISCONTAINER varchar(10);
update ManagedObject set ISCONTAINER='true' where ISCONTAINER like '1';
update ManagedObject set ISCONTAINER='false' where ISCONTAINER like '0';

alter table ManagedObject change column ISGROUP ISGROUP varchar(10);
update ManagedObject set ISGROUP='true' where ISGROUP like '1';
update ManagedObject set ISGROUP='false' where ISGROUP like '0';

alter table ManagedObject change column MANAGED MANAGED varchar(10);
update ManagedObject set MANAGED='true' where MANAGED like '1';
update ManagedObject set MANAGED='false' where MANAGED like '0';

alter table ManagedObject change column STATUSCHANGETIME STATUSCHANGETIME varchar(25) default NULL;
alter table ManagedObject change column STATUSUPDATETIME STATUSUPDATETIME varchar(25) default NULL;

alter table ManagedObject drop column DISCRIMINATOR;
alter table ManagedObject drop column PARENTID;

create index ManagedObject0_ndx on ManagedObject(NAME);
create index ManagedObject1_ndx on ManagedObject (OWNERNAME);
create index ManagedObject2_ndx on ManagedObject (PARENTKEY);

SELECT 'alter table ManagedGroupObject  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table ManagedGroupObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update ManagedGroupObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = ManagedGroupObject.MOID group by ManagedObject.MOID);

SELECT 'alter table TopoObject  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table TopoObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update TopoObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = TopoObject.MOID group by ManagedObject.MOID);

alter table TopoObject change column ISDHCP ISDHCP varchar(10);
update TopoObject set ISDHCP='true' where ISDHCP like '1';
update TopoObject set ISDHCP='false' where ISDHCP like '0';

alter table TopoObject change column ISINTERFACE ISINTERFACE varchar(10);
update TopoObject set ISINTERFACE='true' where ISINTERFACE like '1';
update TopoObject set ISINTERFACE='false' where ISINTERFACE like '0';

alter table TopoObject change column ISNETWORK ISNETWORK varchar(10);
update TopoObject set ISNETWORK='true' where ISNETWORK like '1';
update TopoObject set ISNETWORK='false' where ISNETWORK like '0';

alter table TopoObject change column ISNODE ISNODE varchar(10);
update TopoObject set ISNODE='true' where ISNODE like '1';
update TopoObject set ISNODE='false' where ISNODE like '0';

alter table TopoObject change column ISSNMP ISSNMP varchar(10);
update TopoObject set ISSNMP='true' where ISSNMP like '1';
update TopoObject set ISSNMP='false' where ISSNMP like '0';

SELECT 'alter table Node  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table Node add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update Node set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Node.MOID group by ManagedObject.MOID);

alter table Node change column ISROUTER ISROUTER varchar(10);
update Node set ISROUTER='true' where ISROUTER like '1';
update Node set ISROUTER='false' where ISROUTER like '0';

SELECT 'alter table Network  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table Network add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL  after MOID;
update Network set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Network.MOID group by ManagedObject.MOID);

alter table Network change column DISCOVER DISCOVER varchar(10);
update Network set DISCOVER='true' where DISCOVER like '1';
update Network set DISCOVER='false' where DISCOVER like '0';

SELECT 'alter table SnmpNode  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table SnmpNode add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update SnmpNode set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = SnmpNode.MOID group by ManagedObject.MOID);

SELECT 'alter table SnmpInterface  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table SnmpInterface add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update SnmpInterface set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = SnmpInterface.MOID group by ManagedObject.MOID);

SELECT 'alter table TL1Node  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table TL1Node add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update TL1Node set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID =TL1Node.MOID group by ManagedObject.MOID);

SELECT 'alter table TL1Interface  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table TL1Interface add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update TL1Interface set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = TL1Interface.MOID group by ManagedObject.MOID);

SELECT 'alter table IpAddress  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table IpAddress add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update IpAddress set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = IpAddress.MOID group by ManagedObject.MOID);

SELECT 'alter table SwitchObject  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table SwitchObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update SwitchObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = SwitchObject.MOID group by ManagedObject.MOID);

SELECT 'alter table PortObject  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table PortObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PortObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PortObject.MOID group by ManagedObject.MOID);

SELECT 'alter table Printer  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table Printer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update Printer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Printer.MOID group by ManagedObject.MOID);
alter table Printer drop column CONSOLELIGHTSTRING;

SELECT 'alter table CORBANode  populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';
alter table CORBANode add column NAME varchar(100) first;
alter table CORBANode add column OWNERNAME varchar(25) NOT NULL after MOID;
update CORBANode set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CORBANode.MOID group by ManagedObject.MOID);

SELECT 'alter TOPOUSERPROPS table' AS 'MIGRATION PROCESS STATUS ... ';
alter table TOPOUSERPROPS add column NAME varchar(100) NOT NULL first;
alter table TOPOUSERPROPS add column OWNERNAME varchar(25) NOT NULL;
update TOPOUSERPROPS set NAME=(select ManagedObject.NAME from ManagedObject where TOPOUSERPROPS.MOID=ManagedObject.MOID);
alter table TOPOUSERPROPS drop PRIMARY KEY;
alter table TOPOUSERPROPS add primary key (NAME,OWNERNAME);

SELECT 'alter ObjectSchedulerRUNNABLE table' AS 'MIGRATION PROCESS STATUS ... ';
alter table ObjectSchedulerRUNNABLE add column VALUESTRING varchar(250) NOT NULL first;
update ObjectSchedulerRUNNABLE set VALUESTRING = (select NAME from ManagedObject where ObjectSchedulerRUNNABLE.MOID=ManagedObject.MOID);
alter table ObjectSchedulerRUNNABLE drop primary key;
alter table ObjectSchedulerRUNNABLE add primary key (VALUESTRING);
alter table ObjectSchedulerRUNNABLE drop column MOID;
alter table ObjectSchedulerRUNNABLE drop column CLASSNAME;
alter table ObjectSchedulerRUNNABLE drop index ObjectSchedulerRUNNABLE0_ndx;
create index ObjectSchedulerRUNNABLE0_ndx on ObjectSchedulerRUNNABLE (TIMEVAL);
create index ObjectSchedulerRUNNABLE1_ndx on ObjectSchedulerRUNNABLE (VALUESTRING);
alter table GroupTable add column OWNERNAME varchar (25) NOT NULL after NAME;
alter table GroupTable add column MEMBEROWNERNAME varchar (25) NOT NULL after MEMBERNAME;
alter table GroupTable drop PRIMARY KEY;

SELECT 'create table EVENTLOGGER' AS 'MIGRATION PROCESS STATUS ... ';
create table  EVENTLOGGER (VALUESTRING BIGINT NOT NULL, POSITION INTEGER NOT NULL);
create index EVENTLOGGER0_ndx on EVENTLOGGER(POSITION);

SELECT 'create table EventAlertFilter' AS 'MIGRATION PROCESS STATUS ... ';
create table EventAlertFilter (TYPE varchar(100) NOT NULL, FILTERNAME varchar(100) NOT NULL, ACTIONNAME varchar(100), PROPKEY varchar(100), PROPVALUE blob);

SELECT 'create table GenericFaultTable' AS 'MIGRATION PROCESS STATUS ... ';
create table GenericFaultTable (TYPE varchar(100) NOT NULL, NAME varchar(100) NOT NULL, PRIMARY KEY (TYPE,NAME));

SELECT 'create table TrapEventParser' AS 'MIGRATION PROCESS STATUS ... ';
create table TrapEventParser (TYPE varchar(100) NOT NULL, NAME varchar(100) NOT NULL, PROPKEY varchar(100), PROPVALUE blob);

SELECT 'create table TrapFilter' AS 'MIGRATION PROCESS STATUS ... ';
create table TrapFilter (NAME varchar(100), ENTERPRISEOID varchar(100), GT varchar(10), ST varchar(10), TRAPOID varchar(100), CLASSNAME varchar(100), STATE varchar(50));

SELECT 'create table AgentDefValObject' AS 'MIGRATION PROCESS STATUS ... ';
create table AgentDefValObject (NAME VARCHAR (100) NOT NULL , VALUE VARCHAR (100) , PRIMARY KEY (NAME), index AgentDefValObject0_ndx (NAME));

SELECT 'create table ALERTLOGGER' AS 'MIGRATION PROCESS STATUS ... ';
create table  ALERTLOGGER (VALUESTRING varchar(250));

SELECT 'alter Event table' AS 'MIGRATION PROCESS STATUS ... ';
alter table Event add column DDOMAIN varchar(100) default NULL after CATEGORY;
alter table Event add column HELPURL varchar(100) default NULL after SOURCE;
alter table Event add column OWNERNAME varchar(25) NOT NULL after GROUPNAME;
alter table Event drop column DISCRIMINATOR;

SELECT 'Adding OWNERNAME column to custom Event tables' AS 'MIGRATION PROCESS STATUS ... ';
alter table EMHEvent  add column OWNERNAME varchar(25);
alter table EMHInfoEvent add column OWNERNAME varchar(25);
alter table EMHAlert add column OWNERNAME varchar(25);
alter table ObjectCreationDeletionEvent add column OWNERNAME varchar(25);
alter table StateChangeEvent add column OWNERNAME varchar(25);
alter table UserDefinedEvent add column OWNERNAME varchar(25);
alter table AttributeValueChangeEvent add column OWNERNAME varchar(25);
alter table AvailabilityEvent add column OWNERNAME varchar(25);
alter table RelationshipChangeEvent add column OWNERNAME varchar(25);

create index Event0_ndx on Event (ID);

SELECT 'create table DBEVENT' AS 'MIGRATION PROCESS STATUS ... ';
create table DBEVENT (KEYSTRING VARCHAR(250) NOT NULL, VALUESTRING varchar(250), PRIMARY KEY (KEYSTRING), index DBEVENT0_ndx (KEYSTRING));

SELECT 'alter EVENTUSERPROPS table' AS 'MIGRATION PROCESS STATUS ... ';
alter table EVENTUSERPROPS change column ID NAME INTEGER;
alter table EVENTUSERPROPS add column OWNERNAME varchar(25) NOT NULL after NAME;
alter table EVENTUSERPROPS drop primary key;
create index EVENTUSERPROPS0_ndx on EVENTUSERPROPS (NAME);
create index EVENTUSERPROPS1_ndx  on EVENTUSERPROPS (OWNERNAME);

SELECT 'RENAME AuthAudit table to AuthAuditExt' AS 'MIGRATION PROCESS STATUS ... ';
alter table AuthAudit rename to AuthAuditExt;

SELECT 'alter ManagedObject and hierarchy tables to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter table IpAddress drop column MOID;
alter table ManagedGroupObject drop column MOID;
alter table Network drop column MOID;
alter table Node drop column MOID;
alter table PortObject drop column MOID;
alter table Printer drop column MOID;
alter table SnmpInterface drop column MOID;
alter table SnmpNode drop column MOID;
alter table SwitchObject drop column MOID;
alter table TL1Interface drop column MOID;
alter table TL1Node drop column MOID;
alter table TOPOUSERPROPS drop column MOID;
alter table TopoObject drop column MOID;
alter table ManagedObject drop column MOID;
alter table CORBANode drop column MOID;

SELECT 'alter PhysicalContainer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table PhysicalContainer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PhysicalContainer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PhysicalContainer.MOID group by ManagedObject.MOID);

SELECT 'alter PhysicalElement add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table PhysicalElement add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PhysicalElement set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PhysicalElement.MOID group by ManagedObject.MOID);

SELECT 'alter PhysicalSubUnit add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table PhysicalSubUnit add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PhysicalSubUnit set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PhysicalSubUnit.MOID group by ManagedObject.MOID);

SELECT 'alter PhysicalUnit add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table PhysicalUnit add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PhysicalUnit set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PhysicalUnit.MOID group by ManagedObject.MOID);

SELECT 'alter LogicalContainer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogicalContainer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogicalContainer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogicalContainer.MOID group by ManagedObject.MOID);

SELECT 'alter LogicalElement add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogicalElement add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogicalElement set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogicalElement.MOID group by ManagedObject.MOID);

SELECT 'alter LogicalUnit add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogicalUnit add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogicalUnit set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogicalUnit.MOID group by ManagedObject.MOID);

SELECT 'alter ProtectionGroup add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table ProtectionGroup add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update ProtectionGroup set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = ProtectionGroup.MOID group by ManagedObject.MOID);

SELECT 'alter SBNE add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table SBNE add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update SBNE set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = SBNE.MOID group by ManagedObject.MOID);

SELECT 'alter NetworkElementManagement add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table NetworkElementManagement add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update NetworkElementManagement set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = NetworkElementManagement.MOID group by ManagedObject.MOID);

SELECT 'alter DataObject add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table DataObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update DataObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = DataObject.MOID group by ManagedObject.MOID);

SELECT 'alter IpConfig add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table IpConfig add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update IpConfig set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = IpConfig.MOID group by ManagedObject.MOID);

SELECT 'alter EventControl add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table EventControl add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update EventControl set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = EventControl.MOID group by ManagedObject.MOID);

SELECT 'alter RelationObject add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table RelationObject add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update RelationObject set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = RelationObject.MOID group by ManagedObject.MOID);

SELECT 'alter PhysicalUnitStream add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table PhysicalUnitStream add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update PhysicalUnitStream set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = PhysicalUnitStream.MOID group by ManagedObject.MOID);

SELECT 'alter LogicalUnitStream add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogicalUnitStream add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogicalUnitStream set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogicalUnitStream.MOID group by ManagedObject.MOID);

SELECT 'alter LogStreamDataConfig add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogStreamDataConfig add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogStreamDataConfig set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogStreamDataConfig.MOID group by ManagedObject.MOID);

SELECT 'alter LogStream add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table LogStream add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update LogStream set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = LogStream.MOID group by ManagedObject.MOID);

SELECT 'alter Constituent add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table Constituent add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update Constituent set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Constituent.MOID group by ManagedObject.MOID);

SELECT 'alter ConnectivityData add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table ConnectivityData add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update ConnectivityData set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = ConnectivityData.MOID group by ManagedObject.MOID);

SELECT 'alter Interface add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table Interface add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update Interface set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Interface.MOID group by ManagedObject.MOID);

SELECT 'alter InterfaceContainer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table InterfaceContainer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update InterfaceContainer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = InterfaceContainer.MOID group by ManagedObject.MOID);

SELECT 'alter Endpoint add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table Endpoint add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update Endpoint set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = Endpoint.MOID group by ManagedObject.MOID);

SELECT 'alter BlackBoxLogStream add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table BlackBoxLogStream add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update BlackBoxLogStream set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = BlackBoxLogStream.MOID group by ManagedObject.MOID);

SELECT 'alter CoreFileMgmtLogStream add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CoreFileMgmtLogStream add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CoreFileMgmtLogStream set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CoreFileMgmtLogStream.MOID group by ManagedObject.MOID);

SELECT 'alter HapNeControl add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table HapNeControl add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update HapNeControl set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = HapNeControl.MOID group by ManagedObject.MOID);

SELECT 'alter CnpEthernetPortGroup add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpEthernetPortGroup add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpEthernetPortGroup set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpEthernetPortGroup.MOID group by ManagedObject.MOID);

SELECT 'alter CnpFileSystemInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpFileSystemInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpFileSystemInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpFileSystemInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidPortGroup add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidPortGroup add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidPortGroup set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidPortGroup.MOID group by ManagedObject.MOID);

SELECT 'alter CnpServerInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpServerInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpServerInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpServerInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpEthernetPort add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpEthernetPort add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpEthernetPort set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpEthernetPort.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidPort add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidPort add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidPort set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidPort.MOID group by ManagedObject.MOID);

SELECT 'alter CnpCageInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpCageInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpCageInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpCageInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpACL add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpACL add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpACL set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpACL.MOID group by ManagedObject.MOID);

SELECT 'alter CnpFruInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpFruInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpFruInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpFruInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpSystemInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpSystemInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpSystemInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpSystemInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpLocationInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpLocationInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpLocationInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpLocationInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpMonDevInfo add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpMonDevInfo add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpMonDevInfo set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpMonDevInfo.MOID group by ManagedObject.MOID);

SELECT 'alter CnpCage add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpCage add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpCage set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpCage.MOID group by ManagedObject.MOID);

SELECT 'alter CnpClusterManager add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpClusterManager add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpClusterManager set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpClusterManager.MOID group by ManagedObject.MOID);

SELECT 'alter CnpClusterManagerPG add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpClusterManagerPG add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpClusterManagerPG set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpClusterManagerPG.MOID group by ManagedObject.MOID);

SELECT 'alter CnpEmsServer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpEmsServer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpEmsServer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpEmsServer.MOID group by ManagedObject.MOID);

SELECT 'alter CnpEmsServerPG add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpEmsServerPG add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpEmsServerPG set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpEmsServerPG.MOID group by ManagedObject.MOID);

SELECT 'alter CnpExtSwitch add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpExtSwitch add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpExtSwitch set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpExtSwitch.MOID group by ManagedObject.MOID);

SELECT 'alter CnpFan add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpFan add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpFan set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpFan.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPEM add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPEM add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPEM set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPEM.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaid add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaid add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaid set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaid.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidController add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidController add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidController set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidController.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidFan add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidFan add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidFan set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidFan.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidLogicalDrive add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidLogicalDrive add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidLogicalDrive set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidLogicalDrive.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidLogicalUnit add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidLogicalUnit add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidLogicalUnit set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidLogicalUnit.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidPEM add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidPEM add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidPEM set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidPEM.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRaidPhysicalDrive add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRaidPhysicalDrive add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRaidPhysicalDrive set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRaidPhysicalDrive.MOID group by ManagedObject.MOID);

SELECT 'alter CnpShmm add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpShmm add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpShmm set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpShmm.MOID group by ManagedObject.MOID);

SELECT 'alter CnpSwitch add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpSwitch add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpSwitch set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpSwitch.MOID group by ManagedObject.MOID);

SELECT 'alter CnpTermServer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpTermServer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpTermServer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpTermServer.MOID group by ManagedObject.MOID);

SELECT 'alter CnpSwitchUnit add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpSwitchUnit add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpSwitchUnit set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpSwitchUnit.MOID group by ManagedObject.MOID);

SELECT 'alter CnpRTM add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpRTM add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpRTM set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpRTM.MOID group by ManagedObject.MOID);

SELECT 'alter BlackBoxDataConfig add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table BlackBoxDataConfig add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update BlackBoxDataConfig set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = BlackBoxDataConfig.MOID group by ManagedObject.MOID);

SELECT 'alter CnpAirFilter add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpAirFilter add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpAirFilter set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpAirFilter.MOID group by ManagedObject.MOID);

SELECT 'alter CnpSystemAlarmPanel add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpSystemAlarmPanel add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpSystemAlarmPanel set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpSystemAlarmPanel.MOID group by ManagedObject.MOID);

SELECT 'alter CnpShelfEEPROM add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpShelfEEPROM add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpShelfEEPROM set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpShelfEEPROM.MOID group by ManagedObject.MOID);

SELECT 'alter CnpShmmBMC add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpShmmBMC add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpShmmBMC set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpShmmBMC.MOID group by ManagedObject.MOID);

SELECT 'alter CnpEmsSensor add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpEmsSensor add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpEmsSensor set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpEmsSensor.MOID group by ManagedObject.MOID);

SELECT 'alter CnpExternalServer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpExternalServer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpExternalServer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpExternalServer.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPSBlackBoxLS add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPSBlackBoxLS add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPSBlackBoxLS set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPSBlackBoxLS.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPSCoreFileMgmtLS add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPSCoreFileMgmtLS add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPSCoreFileMgmtLS set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPSCoreFileMgmtLS.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPayloadServer add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPayloadServer add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPayloadServer set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPayloadServer.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPayloadServerPG add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPayloadServerPG add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPayloadServerPG set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPayloadServerPG.MOID group by ManagedObject.MOID);

SELECT 'alter CnpPSBlackBoxDC add NAME AND OWNERNAME columns, populate NAME' AS 'MIGRATION PROCESS STATUS...';
alter table CnpPSBlackBoxDC add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;
update CnpPSBlackBoxDC set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = CnpPSBlackBoxDC.MOID group by ManagedObject.MOID);

SELECT 'alter PhysicalContainer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter PhysicalContainer drop column MOID;

SELECT 'alter PhysicalElement table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter PhysicalElement drop column MOID;

SELECT 'alter PhysicalSubUnit table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter PhysicalSubUnit drop column MOID;

SELECT 'alter PhysicalUnit table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter PhysicalUnit drop column MOID;

SELECT 'alter LogicalContainer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogicalContainer drop column MOID;

SELECT 'alter LogicalElement table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogicalElement drop column MOID;

SELECT 'alter LogicalUnit table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogicalUnit drop column MOID;

SELECT 'alter ProtectionGroup table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter ProtectionGroup drop column MOID;

SELECT 'alter SBNE table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter SBNE drop column MOID;

SELECT 'alter NetworkElementManagement table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter NetworkElementManagement drop column MOID;

SELECT 'alter DataObject table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter DataObject drop column MOID;

SELECT 'alter IpConfig table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter IpConfig drop column MOID;

SELECT 'alter EventControl table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter EventControl drop column MOID;

SELECT 'alter RelationObject table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter RelationObject drop column MOID;

SELECT 'alter PhysicalUnitStream table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter PhysicalUnitStream drop column MOID;

SELECT 'alter LogicalUnitStream table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogicalUnitStream drop column MOID;

SELECT 'alter LogStreamDataConfig table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogStreamDataConfig drop column MOID;

SELECT 'alter LogStream table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter LogStream drop column MOID;

SELECT 'alter Constituent table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter Constituent drop column MOID;

SELECT 'alter ConnectivityData table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter ConnectivityData drop column MOID;

SELECT 'alter Interface table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter Interface drop column MOID;

SELECT 'alter InterfaceContainer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter InterfaceContainer drop column MOID;

SELECT 'alter Endpoint table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter Endpoint drop column MOID;

SELECT 'alter BlackBoxLogStream table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter BlackBoxLogStream drop column MOID;

SELECT 'alter CoreFileMgmtLogStream table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CoreFileMgmtLogStream drop column MOID;

SELECT 'alter HapNeControl table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter HapNeControl drop column MOID;

SELECT 'alter CnpEthernetPortGroup table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpEthernetPortGroup drop column MOID;

SELECT 'alter CnpFileSystemInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpFileSystemInfo drop column MOID;

SELECT 'alter CnpRaidPortGroup table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidPortGroup drop column MOID;

SELECT 'alter CnpServerInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpServerInfo drop column MOID;

SELECT 'alter CnpEthernetPort table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpEthernetPort drop column MOID;

SELECT 'alter CnpRaidPort table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidPort drop column MOID;

SELECT 'alter CnpCageInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpCageInfo drop column MOID;

SELECT 'alter CnpACL table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpACL drop column MOID;

SELECT 'alter CnpFruInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpFruInfo drop column MOID;

SELECT 'alter CnpSystemInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpSystemInfo drop column MOID;

SELECT 'alter CnpLocationInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpLocationInfo drop column MOID;

SELECT 'alter CnpMonDevInfo table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpMonDevInfo drop column MOID;

SELECT 'alter CnpCage table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpCage drop column MOID;

SELECT 'alter CnpClusterManager table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpClusterManager drop column MOID;

SELECT 'alter CnpClusterManagerPG table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpClusterManagerPG drop column MOID;

SELECT 'alter CnpEmsServer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpEmsServer drop column MOID;

SELECT 'alter CnpEmsServerPG table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpEmsServerPG drop column MOID;

SELECT 'alter CnpExtSwitch table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpExtSwitch drop column MOID;

SELECT 'alter CnpFan table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpFan drop column MOID;

SELECT 'alter CnpPEM table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPEM drop column MOID;

SELECT 'alter CnpRaid table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaid drop column MOID;

SELECT 'alter CnpRaidController table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidController drop column MOID;

SELECT 'alter CnpRaidFan table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidFan drop column MOID;

SELECT 'alter CnpRaidLogicalDrive table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidLogicalDrive drop column MOID;

SELECT 'alter CnpRaidLogicalUnit table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidLogicalUnit drop column MOID;

SELECT 'alter CnpRaidPEM table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidPEM drop column MOID;

SELECT 'alter CnpRaidPhysicalDrive table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRaidPhysicalDrive drop column MOID;

SELECT 'alter CnpShmm table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpShmm drop column MOID;

SELECT 'alter CnpSwitch table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpSwitch drop column MOID;

SELECT 'alter CnpTermServer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpTermServer drop column MOID;

SELECT 'alter CnpSwitchUnit table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpSwitchUnit drop column MOID;

SELECT 'alter CnpRTM table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpRTM drop column MOID;

SELECT 'alter BlackBoxDataConfig table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter BlackBoxDataConfig drop column MOID;

SELECT 'alter CnpAirFilter table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpAirFilter drop column MOID;

SELECT 'alter CnpSystemAlarmPanel table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpSystemAlarmPanel drop column MOID;

SELECT 'alter CnpShelfEEPROM table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpShelfEEPROM drop column MOID;

SELECT 'alter CnpShmmBMC table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpShmmBMC drop column MOID;

SELECT 'alter CnpEmsSensor table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpEmsSensor drop column MOID;

SELECT 'alter CnpExternalServer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpExternalServer drop column MOID;

SELECT 'alter CnpPSBlackBoxLS table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPSBlackBoxLS drop column MOID;

SELECT 'alter CnpPSCoreFileMgmtLS table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPSCoreFileMgmtLS drop column MOID;

SELECT 'alter CnpPayloadServer table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPayloadServer drop column MOID;

SELECT 'alter CnpPayloadServerPG table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPayloadServerPG drop column MOID;

SELECT 'alter CnpPSBlackBoxDC table to drop MOID' AS 'MIGRATION PROCESS STATUS ... ';
alter CnpPSBlackBoxDC drop column MOID;

drop table PanelTree;

create table PanelTree (NODEID VARCHAR(100) NOT NULL,NODETYPE VARCHAR(100) NOT NULL,USERNAME VARCHAR(50) NOT NULL,PARENT VARCHAR(100),PREVIOUSNODE VARCHAR(100), MODULENAME varchar(30),PRIMARY KEY (NODEID,USERNAME));
create table PanelProps (NODEID VARCHAR(100) NOT NULL,USERNAME VARCHAR(50) NOT NULL,ATTRIBNAME VARCHAR(30) NOT NULL,ATTRIBVALUE VARCHAR(100),PRIMARY KEY (NODEID,USERNAME,ATTRIBNAME));
