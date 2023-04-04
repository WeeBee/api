package med.voll.api.paciente;

import med.voll.api.endereco.DadosEndereco;

public class DadosCadastroPaciente {

	private final String nome;
	private final String email;
	private final String telefone;
	private final String cpf;
	private final DadosEndereco endereco;
	
	public DadosCadastroPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public DadosEndereco getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "DadosCadastroPaciente [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf
				+ ", endereco=" + endereco + "]";
	}
	
}
