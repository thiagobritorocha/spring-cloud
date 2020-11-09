package br.com.linkstart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.linkstart.data.vo.VendaVO;
import lombok.Data;

@Entity
@Table(name="venda")
@Data
public class Venda implements Serializable {
	
	private static final long serialVersionUID = 8050219110953727503L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name="data", nullable = false)
	private LocalDate data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy =  "venda", cascade = CascadeType.REFRESH)
	private List<ProdutoVenda> produtos;
	
	@Column(name="valor_total", nullable = false, length = 10)
	private BigDecimal valorTotal;

	public static Venda create(VendaVO vendaVO) {
		return new ModelMapper().map(vendaVO, Venda.class);
	}
}
