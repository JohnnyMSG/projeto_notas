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

import br.com.sonner.notas.controller.dto.ItemNotaDto;
import br.com.sonner.notas.controller.form.ItemNotaForm;
import br.com.sonner.notas.modelo.ItemNota;
import br.com.sonner.notas.repository.ItemNotaRepository;
import br.com.sonner.notas.repository.NotaRepository;
import br.com.sonner.notas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itensNota")
public class ItemNotaController {

	@Autowired
	private ItemNotaRepository itemNotaRepository;
	@Autowired
	private NotaRepository notaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<ItemNotaDto> listaFornecedores() {
		List<ItemNota> itens = itemNotaRepository.findAll();
		return ItemNotaDto.converter(itens);
	}

	// Create
	@PostMapping
	@Transactional
	public ResponseEntity<ItemNotaDto> cadastrar(@RequestBody @Valid ItemNotaForm form,
			UriComponentsBuilder uriBuilder) {
		ItemNota itemNota = form.converter(notaRepository, produtoRepository, itemNotaRepository);
		//itemNotaRepository.save(itemNota);

		URI uri = uriBuilder.path("/itensNota/{id}").buildAndExpand(itemNota.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemNotaDto(itemNota));
	}

	// Read
	@GetMapping("/{id}")
	public ItemNotaDto leitura(@PathVariable("id") Long id) {
		ItemNota itemNota = itemNotaRepository.getReferenceById(id);

		return new ItemNotaDto(itemNota);
	}

	// Update
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemNotaDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid ItemNotaForm form) {
		ItemNota itemNota = form.atualizar(id, itemNotaRepository, notaRepository, produtoRepository);

		return ResponseEntity.ok(new ItemNotaDto(itemNota));
	}

	// Delete
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		itemNotaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
