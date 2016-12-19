	package br.com.asga.ems.asga4e1int.inventory;

	import java.util.Properties;

	public class Tabela implements java.io.Serializable{

		private String descricao;
		private ChaveTabela id = new ChaveTabela();

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public void setId(ChaveTabela id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public ChaveTabela getId() {
			return id;
		}

		public void setHost(String host) {
			this.id.setHost(host);
		}

		public void setInteiro(int inteiro) {
			this.id.setInteiro(inteiro);
		}

		public String getHost() {
			return id.getHost();
		}

		public int getInteiro() {
			return id.getInteiro();
		}

		public void setProperties(Properties props) {
			String descricao_value = (String) props.remove("descricao");
			if (descricao_value != null) {
				descricao = descricao_value;
			}

			String host_value = props.getProperty("host");
			if (host_value != null) {
				id.setHost(host_value);
			}

			String inteiro_value = props.getProperty("inteiro");
			if (inteiro_value != null) {
				id.setInteiro(Integer.parseInt(inteiro_value));
			}
			;
		}

		public Properties getProperties() {
			Properties props = new Properties();
			if (getDescricao() != null) {
				props.put("descricao", getDescricao());
			}

			if (getHost() != null) {
				props.put("host", id.getHost());
			}

			props.put("inteiro", String.valueOf(getInteiro()));
			return props;
		}

	}
