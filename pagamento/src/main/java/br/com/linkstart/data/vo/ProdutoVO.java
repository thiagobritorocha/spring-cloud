package br.com.linkstart.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.linkstart.entity.Produto;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"id","estoque"})
@Getter
@Setter
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable{

	private static final long serialVersionUID = -903969559709224578L;
	
	@JsonPropertyOrder("id")
	private Long id;
	
	@JsonPropertyOrder("estoque")
	private Integer estoque;	
	
	public static ProdutoVO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoVO.class);
	}

}
