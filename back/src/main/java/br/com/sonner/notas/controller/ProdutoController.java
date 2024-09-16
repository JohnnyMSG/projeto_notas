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

import br.com.sonner.notas.controller.dto.ProdutoDto;
import br.com.sonner.notas.controller.form.ProdutoForm;
import br.com.sonner.notas.modelo.Produto;
import br.com.sonner.notas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<ProdutoDto> listaProdutos() {
		List<Produto> produtos = produtoRepository.findAll();
		
		return ProdutoDto.converter(produtos);
	}
	
	//Create
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter();
		produtoRepository.save(produto);
		
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	//Read
	@GetMapping("/{id}")
	public ProdutoDto leitura(@PathVariable("id") Long id) {
		Produto produto = produtoRepository.getReferenceById(id);

		return new ProdutoDto(produto);	
	}
	
	//Update
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid ProdutoForm form) {
		Produto produto = form.atualizar(id, produtoRepository);
		
		return ResponseEntity.ok(new ProdutoDto(produto));
	}
	
	//Delete
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}






