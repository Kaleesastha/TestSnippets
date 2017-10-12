	package test;

	import java.util.Properties;

	public class SecondChildMO extends com.adventnet.nms.topodb.ManagedObject {

		private String sysTest;

		public SecondChildMO() {
			setClassname("SecondChildMO");
		}

		public void setSysTest(String sysTest) {
			this.sysTest = sysTest;
		}

		public String getSysTest() {
			return sysTest;
		}

		public void setProperties(Properties props) {
			String sysTest_value = (String) props.remove("sysTest");
			if (sysTest_value != null) {
				sysTest = sysTest_value;
			}
			super.setProperties(props);
		}

		public Properties getProperties() {
			Properties props = super.getProperties();
			if (getSysTest() != null) {
				props.put("sysTest", getSysTest());
			}
			return props;
		}

	}
