package br.com.sonner.notas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sonner.notas.controller.dto.NotaDto;
import br.com.sonner.notas.controller.form.NotaForm;
import br.com.sonner.notas.modelo.Nota;
import br.com.sonner.notas.repository.FornecedorRepository;
import br.com.sonner.notas.repository.ItemNotaRepository;
import br.com.sonner.notas.repository.NotaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notas")
public class NotaController {

	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ItemNotaRepository itemNotaRepository;

	@GetMapping
	public List<NotaDto> listaFornecedores() {
		List<Nota> notas = notaRepository.findAll();
		return NotaDto.converter(notas);
	}

	// Create
	@PostMapping
	@Transactional
	public ResponseEntity<NotaDto> cadastrar(@RequestBody @Valid NotaForm form,
			UriComponentsBuilder uriBuilder) {
		Nota nota = form.converter(fornecedorRepository, notaRepository, itemNotaRepository);
		notaRepository.save(nota);

		URI uri = uriBuilder.path("/notas/{id}").buildAndExpand(nota.getId()).toUri();
		return ResponseEntity.created(uri).body(new NotaDto(nota));
	}

	// Read
	@GetMapping("/{id}")
	public NotaDto leitura(@PathVariable("id") Long id) {
		Nota nota = notaRepository.getReferenceById(id);

		return new NotaDto(nota);
	}

	// Update
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<NotaDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid NotaForm form) {
		Nota fornecedor = form.atualizar(id, notaRepository, fornecedorRepository, itemNotaRepository);

		return ResponseEntity.ok(new NotaDto(fornecedor));
	}

	// Delete
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		notaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
