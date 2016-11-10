	package corechassis.ngems.devicemodel;

	import java.util.Properties;

	public class XMLNode extends com.adventnet.nms.topodb.Node {

		private int xmlPort;
		private String location;
		private String regionCode;
		private int noOfShelves;
		private String nodeStatus;
		private String AdminStatus;
		private String latitude;
		private String longitude;
		private String bkpSchedule;
		private String VIPStatus; 

		public XMLNode() {
			setClassname("XMLNode");
		}

		public void setXmlPort(int xmlPort) {
			this.xmlPort = xmlPort;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public void setRegionCode(String regionCode) {
			this.regionCode = regionCode;
		}

		public void setNoOfShelves(int noOfShelves) {
			this.noOfShelves = noOfShelves;
		}

		public void setNodeStatus(String nodeStatus) {
			this.nodeStatus = nodeStatus;
		}

		public void setAdminStatus(String AdminStatus) {
			this.AdminStatus = AdminStatus;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public void setBkpSchedule(String bkpschedule) {
			this.bkpSchedule = bkpschedule;
		}

		public void setVIPStatus(String VIPStatus) {
			this.VIPStatus = VIPStatus;
		}


		public int getXmlPort() {
			return xmlPort;
		}

		public String getLocation() {
			return location;
		}

		public String getRegionCode() {
			return regionCode;
		}

		public int getNoOfShelves() {
			return noOfShelves;
		}

		public String getNodeStatus() {
			return nodeStatus;
		}

		public String getAdminStatus() {
			return AdminStatus;
		}

		public String getLatitude() {
			return latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public String getBkpSchedule() {
			return bkpSchedule;
		}
		public String getVIPStatus() {
			return VIPStatus;
		}

		public void setProperties(Properties props) {
			String xmlPort_value = (String) props.remove("xmlPort");
			if (xmlPort_value != null) {
				xmlPort = Integer.parseInt(xmlPort_value);
			}

			String location_value = (String) props.remove("location");
			if (location_value != null) {
				location = location_value;
			}

			String regionCode_value = (String) props.remove("regionCode");
			if (regionCode_value != null) {
				regionCode = regionCode_value;
			}

			String noOfShelves_value = (String) props.remove("noOfShelves");
			if (noOfShelves_value != null) {
				noOfShelves = Integer.parseInt(noOfShelves_value);
			}

			String nodeStatus_value = (String) props.remove("nodeStatus");
			if (nodeStatus_value != null) {
				nodeStatus = nodeStatus_value;
			}

			String AdminStatus_value = (String) props.remove("AdminStatus");
			if (AdminStatus_value != null) {
				AdminStatus = AdminStatus_value;
			}

			String latitude_value = (String) props.remove("latitude");
			if (latitude_value != null) {
				latitude = latitude_value;
			}

			String longitude_value = (String) props.remove("longitude");
			if (longitude_value != null) {
				longitude = longitude_value;
			}

			String bkpSchedule_value = (String) props.remove("bkpSchedule");
			if (bkpSchedule_value != null) {
				bkpSchedule = bkpSchedule_value;
			}

			String VIPStatus_value = (String) props.remove("VIPStatus");
			if (VIPStatus_value != null) {
				VIPStatus = VIPStatus_value;
			}

			super.setProperties(props);
		}

		public Properties getProperties() {
			Properties props = super.getProperties();
			props.put("xmlPort", String.valueOf(getXmlPort()));

			if (getLocation() != null) {
				props.put("location", getLocation());
			}

			if (getRegionCode() != null) {
				props.put("regionCode", getRegionCode());
			}

			props.put("noOfShelves", String.valueOf(getNoOfShelves()));

			if (getNodeStatus() != null) {
				props.put("nodeStatus", getNodeStatus());
			}

			if (getAdminStatus() != null) {
				props.put("AdminStatus", getAdminStatus());
			}

			if (getLatitude() != null) {
				props.put("latitude", getLatitude());
			}

			if (getLongitude() != null) {
				props.put("longitude", getLongitude());
			}

			if (getBkpSchedule() != null) {
				props.put("bkpSchedule", getBkpSchedule());
			}
			if (getVIPStatus() != null) {
				props.put("VIPStatus", getVIPStatus());
			}

			return props;
		}

	}
