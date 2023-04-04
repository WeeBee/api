package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

public class DadosCadastroMedico {
	
	private final String nome;
	private final String email;
	private final String crm;
	private final Especialidade especialidade;
	private final DadosEndereco endereco;

	public DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
		this.nome = nome;
		this.email = email;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCrm() {
		return crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public DadosEndereco getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "DadosCadastroMedico [nome=" + nome + ", email=" + email + ", crm=" + crm + ", especialidade="
				+ especialidade + ", endereco=" + endereco + "]";
	}
}
