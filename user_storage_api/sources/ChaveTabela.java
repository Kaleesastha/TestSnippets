	package br.com.asga.ems.asga4e1int.inventory;

	import java.util.Properties;

	public class ChaveTabela implements java.io.Serializable {

		private String host;
		private int inteiro;

		public void setHost(String host) {
			this.host = host;
		}

		public void setInteiro(int inteiro) {
			this.inteiro = inteiro;
		}

		public String getHost() {
			return host;
		}

		public int getInteiro() {
			return inteiro;
		}
	}
