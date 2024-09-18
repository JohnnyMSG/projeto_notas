package br.com.sonner.notas.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sonner.notas.modelo.Fornecedor;
import br.com.sonner.notas.modelo.ItemNota;
import br.com.sonner.notas.modelo.Nota;

public class NotaDto {

    private Long id;
    private int numero;
    private LocalDate data = LocalDate.now();
    private String nomeFornecedor;
    private List<ItemNotaDto> itens = new ArrayList<>();

    public NotaDto(Nota nota) {
        this.id = nota.getId();
        this.numero = nota.getNumero();
        this.nomeFornecedor = nota.getFornecedor().getNome();
        this.itens = ItemNotaDto.converter(nota.getItens());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public List<ItemNotaDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemNotaDto> itens) {
        this.itens = itens;
    }

    public static List<NotaDto> converter(List<Nota> notas) {
        return notas.stream().map(NotaDto::new).collect(Collectors.toList());
    }
}
