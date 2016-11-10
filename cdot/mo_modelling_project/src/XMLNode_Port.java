package corechassis.ngems.devicemodel;

	import java.util.Properties;

	public class XMLNode_Port extends com.adventnet.nms.topodb.ManagedObject {

		private String portIP;
		private String portOpStatus;
		private String regionCode;
		private String adminStatus;
		private String purpose;
		private String macAddress;
		private int mtu;
		private String portGroup;

		public XMLNode_Port() {
			setClassname("XMLNode_Port");
		}

		public void setPortIP(String portIP) {
			this.portIP = portIP;
		}

		public void setPortOpStatus(String portOpStatus) {
			this.portOpStatus = portOpStatus;
		}

		public void setRegionCode(String regionCode) {
			this.regionCode = regionCode;
		}

		public void setAdminStatus(String adminStatus) {
			this.adminStatus = adminStatus;
		}

		public void setPurpose(String purpose) {
			this.purpose = purpose;
		}

		public void setMacAddress(String macAddress) {
			this.macAddress = macAddress;
		}

		public void setMtu(int mtu) {
			this.mtu = mtu;
		}

		public void setPortGroup(String portGroup) {
			this.portGroup = portGroup;
		}

		public String getPortIP() {
			return portIP;
		}

		public String getPortOpStatus() {
			return portOpStatus;
		}

		public String getRegionCode() {
			return regionCode;
		}

		public String getAdminStatus() {
			return adminStatus;
		}

		public String getPurpose() {
			return purpose;
		}

		public String getMacAddress() {
			return macAddress;
		}

		public int getMtu() {
			return mtu;
		}

		public String getPortGroup() {
			return portGroup;
		}

		public void setProperties(Properties props) {
			String portIP_value = (String) props.remove("portIP");
			if (portIP_value != null) {
				portIP = portIP_value;
			}

			String portOpStatus_value = (String) props.remove("portOpStatus");
			if (portOpStatus_value != null) {
				portOpStatus = portOpStatus_value;
			}

			String regionCode_value = (String) props.remove("regionCode");
			if (regionCode_value != null) {
				regionCode = regionCode_value;
			}

			String adminStatus_value = (String) props.remove("adminStatus");
			if (adminStatus_value != null) {
				adminStatus = adminStatus_value;
			}

			String purpose_value = (String) props.remove("purpose");
			if (purpose_value != null) {
				purpose = purpose_value;
			}

			String macAddress_value = (String) props.remove("macAddress");
			if (macAddress_value != null) {
				macAddress = macAddress_value;
			}

			String mtu_value = (String) props.remove("mtu");
			if (mtu_value != null) {
				mtu = Integer.parseInt(mtu_value);
			}

			String portGroup_value = (String) props.remove("portGroup");
			if (portGroup_value != null) {
				portGroup = portGroup_value;
			}
			super.setProperties(props);
		}

		public Properties getProperties() {
			Properties props = super.getProperties();
			if (getPortIP() != null) {
				props.put("portIP", getPortIP());
			}

			if (getPortOpStatus() != null) {
				props.put("portOpStatus", getPortOpStatus());
			}

			if (getRegionCode() != null) {
				props.put("regionCode", getRegionCode());
			}

			if (getAdminStatus() != null) {
				props.put("adminStatus", getAdminStatus());
			}

			if (getPurpose() != null) {
				props.put("purpose", getPurpose());
			}

			if (getMacAddress() != null) {
				props.put("macAddress", getMacAddress());
			}

			props.put("mtu", String.valueOf(getMtu()));

			if (getPortGroup() != null) {
				props.put("portGroup", getPortGroup());
			}
			return props;
		}

	}

