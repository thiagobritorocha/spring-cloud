package br.com.linkstart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.linkstart.data.vo.VendaVO;
import br.com.linkstart.entity.ProdutoVenda;
import br.com.linkstart.entity.Venda;
import br.com.linkstart.exception.ResourceNotFoundException;
import br.com.linkstart.repository.ProdutoVendaRepository;
import br.com.linkstart.repository.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	public VendaVO create(VendaVO  vendaVO) {
		Venda venda =  vendaRepository.save(Venda.create(vendaVO));
		
		List<ProdutoVenda> produtoSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtoSalvos.add(produtoVendaRepository.save(pv));
		});
		venda.setProdutos(produtoSalvos);
		
		return VendaVO.create(venda);		
	}
	
	public Page<VendaVO> findAll(Pageable pageable){
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}
	
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return VendaVO.create(entity);
	}
	
	public VendaVO update(VendaVO vendaVO) {
		final Optional<Venda> optiVenda = vendaRepository.findById(vendaVO.getId());
		
		if(!optiVenda.isPresent()) {
			 new ResourceNotFoundException("No records found for this ID");
		}
		
		return VendaVO.create(vendaRepository.save(Venda.create(vendaVO)));
	}
	
	public void delete(Long id) {
		var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		vendaRepository.delete(entity);
	}
}
