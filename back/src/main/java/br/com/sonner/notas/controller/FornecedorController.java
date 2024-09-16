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

import br.com.sonner.notas.controller.dto.FornecedorDto;
import br.com.sonner.notas.controller.form.FornecedorForm;
import br.com.sonner.notas.modelo.Fornecedor;
import br.com.sonner.notas.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@GetMapping
	public List<FornecedorDto> listaFornecedores() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		return FornecedorDto.converter(fornecedores);
	}

	// Create
	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorForm form,
			UriComponentsBuilder uriBuilder) {
		Fornecedor fornecedor = form.converter();
		fornecedorRepository.save(fornecedor);

		URI uri = uriBuilder.path("/fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
	}

	// Read
	@GetMapping("/{id}")
	public FornecedorDto leitura(@PathVariable("id") Long id) {
		Fornecedor fornecedor = fornecedorRepository.getReferenceById(id);

		return new FornecedorDto(fornecedor);
	}

	// Update
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid FornecedorForm form) {
		Fornecedor fornecedor = form.atualizar(id, fornecedorRepository);

		return ResponseEntity.ok(new FornecedorDto(fornecedor));
	}

	// Delete
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		fornecedorRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
