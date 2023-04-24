package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
	
	@Autowired
	private AgendaDeConsultas agenda;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dto = agenda.agendar(dados);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping
	public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
		agenda.cancelar(dados);
		return ResponseEntity.noContent().build();
	}
}
