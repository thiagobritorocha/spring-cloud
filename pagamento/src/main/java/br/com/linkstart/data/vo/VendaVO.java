package br.com.linkstart.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.linkstart.entity.ProdutoVenda;
import br.com.linkstart.entity.Venda;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "data", "produtos", "valorTotal" })
@Getter
@Setter
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

	private static final long serialVersionUID = -903969559709224578L;

	@JsonPropertyOrder("id")
	private Long id;

	@JsonPropertyOrder("data")
	private LocalDate data;

	@JsonPropertyOrder("produtos")
	private List<ProdutoVenda> produtos;

	@JsonPropertyOrder("valorTotal")
	private BigDecimal valorTotal;

	public static VendaVO create(Venda venda) {
		return new ModelMapper().map(venda, VendaVO.class);
	}

}
