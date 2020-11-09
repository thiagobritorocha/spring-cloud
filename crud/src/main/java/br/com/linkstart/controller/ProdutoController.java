package br.com.linkstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.linkstart.data.vo.ProdutoVO;
import br.com.linkstart.service.ProdutoService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/produto")
public class ProdutoController {


	private final ProdutoService produtoService;
	private final PagedResourcesAssembler<ProdutoVO> assembler;
	
	@Autowired
	public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler<ProdutoVO> assembler) {
		this.produtoService = produtoService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ProdutoVO findById(@PathVariable("id") long id) {
		ProdutoVO produtoVo =  produtoService.findById(id);
		produtoVo.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
		return produtoVo;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		var sortDirection = "desc".equals(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
		
		Page<ProdutoVO> produtos = produtoService.findAll(pageable);
		
		produtos.stream()
			.forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ProdutoVO>> pageModel = assembler.toModel(produtos);
		
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ProdutoVO create(@RequestBody ProdutoVO produtoVO) {
		ProdutoVO produto = produtoService.create(produtoVO);
		produto.add(linkTo(methodOn(ProdutoController.class).findById(produto.getId())).withSelfRel());
		return produto;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ProdutoVO update(@RequestBody ProdutoVO produtoVO) {
		ProdutoVO produto = produtoService.update(produtoVO);
		produto.add(linkTo(methodOn(ProdutoController.class).findById(produto.getId())).withSelfRel());
		return produto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id ){
		produtoService.delete(id);
		return ResponseEntity.ok().build();
	}
}
