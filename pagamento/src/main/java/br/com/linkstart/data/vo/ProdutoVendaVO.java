package br.com.linkstart.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.linkstart.entity.ProdutoVenda;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id","quantidade"})
@Getter
@Setter
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable{

	private static final long serialVersionUID = -3874112498381418187L;
	
	@JsonPropertyOrder("id")
	private Long id;
	
	@JsonPropertyOrder("id_produto")
	private Long idProduto;
	
	@JsonPropertyOrder("quantidade")
	private Integer quantidade;
	
	public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
	}

}
