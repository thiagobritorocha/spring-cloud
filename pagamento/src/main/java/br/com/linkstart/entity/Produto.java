package br.com.linkstart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.linkstart.data.vo.ProdutoVO;
import lombok.Data;

@Entity
@Table(name="produto")
@Data
public class Produto {
	
	@Id
	private Long id;
	
	@Column(name="estoque", nullable = false, length = 10)
	private Integer estoque;
	
	public static Produto create(ProdutoVO produtoVO) {
		return new ModelMapper().map(produtoVO, Produto.class);
	}

}
