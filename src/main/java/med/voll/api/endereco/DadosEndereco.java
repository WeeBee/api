package med.voll.api.endereco;

public class DadosEndereco {
	
	private final String logradouro;
	private final String bairro;
	private final String cep;
	private final String cidade;
	private final String uf;
	private final String complemento;
	private final String numero;

	public DadosEndereco(String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.complemento = complemento;
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "DadosEndereco [logradouro=" + logradouro + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + ", complemento=" + complemento + ", numero=" + numero + "]";
	}
	
	
}
