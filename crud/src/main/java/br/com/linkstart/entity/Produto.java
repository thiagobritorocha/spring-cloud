package br.com.linkstart.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.linkstart.data.vo.ProdutoVO;
import lombok.Data;

@Entity
@Table(name="produto")
@Data
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome", nullable = false, length = 255)
	private String nome;
	@Column(name="estoque", nullable = false, length = 10)
	private Integer estoque;
	@Column(name="preco", nullable = false, length = 10)
	private BigDecimal preco;
	
	public static Produto create(ProdutoVO produto) {
		return new ModelMapper().map(produto, Produto.class);
	}
}
