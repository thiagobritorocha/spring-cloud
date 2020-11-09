package br.com.linkstart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="produto_venda")
@Data
public class ProdutoVenda implements Serializable {

	private static final long serialVersionUID = -8427603521233501413L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_produto", nullable = false, length = 10)
	private Long idProduto;
	
	@Column(name="quantidade", nullable = false, length = 255)
	private Integer quantidade;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venda")
	private Venda venda; 
	
	
	public static ProdutoVenda create(ProdutoVenda p) {
		return new ModelMapper().map(p, ProdutoVenda.class);
	}

}
