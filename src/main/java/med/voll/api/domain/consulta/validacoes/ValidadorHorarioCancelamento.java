package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.controller.DadosCancelamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;

@Component
public class ValidadorHorarioCancelamento {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosCancelamentoConsulta dados) {
		var consulta = repository.getReferenceById(dados.idConsulta());
		var agora = LocalDateTime.now();
		var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
		
		if (diferencaEmHoras < 24) {
			throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24H!");
		}
	}
}
