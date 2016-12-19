SELECT 'DROPPING ALL FOREIGN KEYS' AS 'MIGRATION PROCESS STATUS ... ';
SET FOREIGN_KEY_CHECKS=0;
SET GLOBAL FOREIGN_KEY_CHECKS=0;
#alter table icssp4.ALERTUSERPROPS DROP FOREIGN KEY FKCFB5A6A93E26DDB;
#alter table icssp4.CCTVVIEWS DROP FOREIGN KEY CCTV_ID;
#alter table icssp4.CCTVVIEWS DROP FOREIGN KEY DASHBOARD_ID;
#alter table icssp4.CORBANode DROP FOREIGN KEY FK427DD3C7435BA95;
#alter table icssp4.CRITERIAPROPERTIES DROP FOREIGN KEY FK435EDDD2977A5201;
#alter table icssp4.CustomMatchCriteria DROP FOREIGN KEY FK89FED43325ACDCD2;
#alter table icssp4.DASHBOARDCOLUMNS DROP FOREIGN KEY DASHBOARDCOLUMN_ID;
#alter table icssp4.DASHBOARDPROPS DROP FOREIGN KEY DASHBOARDPROPS_ID;
#alter table icssp4.DEVICELIST_AGENTNAMES DROP FOREIGN KEY FKDD1A908EF8844ACD;
#alter table icssp4.DeviceCompSchedule DROP FOREIGN KEY FK9E94845C901D212;
#alter table icssp4.DeviceDSPolledData DROP FOREIGN KEY FK18865CCD44CED9A2;
#alter table icssp4.EVENTUSERPROPS DROP FOREIGN KEY FK54B8432B87584B8F;
#alter table icssp4.GMapSymbol DROP FOREIGN KEY FK6E7E118DB80FB2C3;
#alter table icssp4.IpAddress DROP FOREIGN KEY FKD8D77CAD7825E164;
#alter table icssp4.KPICustomDS DROP FOREIGN KEY FK65733F64B72CEF94;
#alter table icssp4.KPIDeviceDS DROP FOREIGN KEY FK836EAD09D5285D39;
#alter table icssp4.KPIPollDS DROP FOREIGN KEY FK8AE409326AC762E2;
#alter table icssp4.KPIPolledData DROP FOREIGN KEY FK33D8000CD723312D;
#alter table icssp4.KPISqlDS DROP FOREIGN KEY FK784309B9B4F03C9D;
#alter table icssp4.KPITOSUBCOMPUTATION DROP FOREIGN KEY FK62B67A46633F77BB;
#alter table icssp4.KPITOSUBCOMPUTATION DROP FOREIGN KEY FK62B67A466A696AA8;
#alter table icssp4.MAPPEDPROPERTIES DROP FOREIGN KEY FK4D2F47A6977A5201;
#alter table icssp4.MAPUSERPROPS DROP FOREIGN KEY FK30B70EA9AF738122;
#alter table icssp4.MOMatchCriteria DROP FOREIGN KEY FK631E5C2D09CDD43;
#alter table icssp4.ManagedGroupObject DROP FOREIGN KEY FK2D38159FD43F4EA2;
#alter table icssp4.ManagedObject DROP FOREIGN KEY FKB855B9E41BE4C5D;
#alter table icssp4.MapContainer DROP FOREIGN KEY FKFA2B62A5334AE2DB;
#alter table icssp4.MapGroup DROP FOREIGN KEY FKD33BEA36F4BC0D9;
#alter table icssp4.MapLink DROP FOREIGN KEY FK951453561311A184;
#alter table icssp4.MultipleDeviceDSPolledData DROP FOREIGN KEY FK8C5D3AFD567F5DD2;
#alter table icssp4.Network DROP FOREIGN KEY FKD119F20E5044AD45;
#alter table icssp4.Node DROP FOREIGN KEY FK2522223D370DA5;
#alter table icssp4.POLLUSERPROPS DROP FOREIGN KEY FK5DBA4B86D7D7502;
#alter table icssp4.PortObject DROP FOREIGN KEY FK679DDF409E30C459;
#alter table icssp4.Printer DROP FOREIGN KEY FK50765FFA21D93CDB;
#alter table icssp4.SnmpInterface DROP FOREIGN KEY FK7DB9517B6E19E932;
#alter table icssp4.SnmpNode DROP FOREIGN KEY FK293EA880896A8103;
#alter table icssp4.SwitchObject DROP FOREIGN KEY FK4C0B63B3695F01CC;
#alter table icssp4.TL1Interface DROP FOREIGN KEY FK11A58880F6B1DA18;
#alter table icssp4.TL1Node DROP FOREIGN KEY FKE013625BB76D185D;
#alter table icssp4.TOPOUSERPROPS DROP FOREIGN KEY FK483583EBFBE5F355;
#alter table icssp4.TopoObject DROP FOREIGN KEY FK608221B9A4FF80BC;
#alter table icssp4.WIDGET DROP FOREIGN KEY WIDGET_CRITERIAID;
#alter table icssp4.WIDGET DROP FOREIGN KEY WIDGET_DATASOURCEID;
#alter table icssp4.WIDGETASSOCIATION DROP FOREIGN KEY WIDGETASSOCIATION_DASHBOARDID;
#alter table icssp4.WIDGETASSOCIATION DROP FOREIGN KEY WIDGETASSOCIATION_WIDGETID;
#alter table icssp4.WIDGETLEVEL DROP FOREIGN KEY WIDGETLEVEL_ID;

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
drop table PanelTree;

create table PanelTree (NODEID VARCHAR(100) NOT NULL,NODETYPE VARCHAR(100) NOT NULL,USERNAME VARCHAR(50) NOT NULL,PARENT VARCHAR(100),PREVIOUSNODE VARCHAR(100), MODULENAME varchar(30),PRIMARY KEY (NODEID,USERNAME));
create table PanelProps (NODEID VARCHAR(100) NOT NULL,USERNAME VARCHAR(50) NOT NULL,ATTRIBNAME VARCHAR(30) NOT NULL,ATTRIBVALUE VARCHAR(100),PRIMARY KEY (NODEID,USERNAME,ATTRIBNAME));
