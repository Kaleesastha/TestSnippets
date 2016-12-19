package test;
public class QueriesForNewTables 
{
	public static void main(String args[])
	{
		String tables[] ={"AckFwdSystemLink", "BackupServerLink", "BillingServerLink", "BmgwClientThrottleLink", "BmgwServerThrottleLink", "BmgwServerUnavailableLink", "CapacityLicenseLink", "ClassOfService", "Congestion", "ContentAdaptationLink", "DiskUsage", "DnsServerLink", "EnumServerLink", "EnumServiceLink", "EsmeLink", "EsmeLocalConnectionLink", "EsmeNetworkConnectionLink", "EsmeQueuedMessageLink", "ExternalIPLink", "FileSystem", "GeoRedSMSReplLink", "GeoRedSMSReplThrottleLink", "HssServerLink", "HssServerThrottleLink", "HssServerUnavailableConnection", "ImsGsmGwClientThrottleLink", "ImsGsmGwServerLink", "ImsGsmGwServerThrottleLink", "InterMateLink", "LnpServerLink", "LoadBalancerLink", "M2paLink", "M2paLogicalUnit", "M2paSystemInfo", "MateLink", "MessagingSystem", "Mm1Link", "MpSystemConfig", "MsgArchiveServerLink", "MsgBladeLU", "MsgBladeLUContainer", "MsmMemUtilization", "PeerMMSCLink", "PersonalizationConfig", "PrepaidServerLink", "PrepaidServerThrottleLink", "QueueOverflow", "RemoteSMSCThrottleLink", "SCSCFClientThrottleLink", "SCSCFServerThrottleLink", "SmppClientLink", "SmppServerLink", "SmscLink", "SmscLocalConnectionLink", "SmscNetworkConnectionLink", "SmscQueuedMessageLink", "SmtpClientLink", "SmtpLink", "SmtpLocalConnectionLink", "SmtpNetworkConnectionLink", "SmtpQueuedMessageLink", "SmtpServerLink", "SpamServerLink", "SpamServerThrottleLink", "SpamServerUnavailableConnection", "Ss7AssociationLink", "Ss7PCLink", "Ss7Service", "StatSizeAuditLink", "StatisticsThreshold", "SubLdapServerLink", "TapClientLink", "TapLink", "TapLocalConnectionLink", "TapNetworkConnectionLink", "TranscoderLink", "UsageControlServiceLink", "VaspLink", "XmlClientLink", "XmlcLocalConnectionLink", "XmlcNetworkConnectionLink"};

		for (int k=0;k<tables.length;k++){
			String tableName = tables[k];
			System.err.println("SELECT 'alter table "+tableName+" populate NAME and drop MOID column' AS 'MIGRATION PROCESS STATUS ... ';");
			System.err.println("alter table "+tableName+" add column NAME varchar(100) default NULL, add column OWNERNAME varchar(25) NOT NULL after MOID;");
			System.err.println("update " +tableName+" set NAME=(select ManagedObject.NAME from ManagedObject where ManagedObject.MOID = "+tableName+".MOID group by ManagedObject.MOID);");
			System.err.println("alter table "+tableName+" drop column MOID;\n");
		}
	}
}
