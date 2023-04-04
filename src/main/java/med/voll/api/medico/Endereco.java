package med.voll.api.medico;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	
	public Endereco() {} // lombok não tá funcionado??? tá precisando do construtor padrão apesar da annotation @NoArgsConstructor... :| (@WeeBee)
	
	public Endereco(DadosEndereco dados) {
		this.logradouro = dados.getLogradouro();
		this.bairro = dados.getBairro();
		this.cep = dados.getCep();
		this.numero = dados.getNumero();
		this.complemento = dados.getComplemento();
		this.cidade = dados.getCidade();
		this.uf = dados.getUf();
	}
}
