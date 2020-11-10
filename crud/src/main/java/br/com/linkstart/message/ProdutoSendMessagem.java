package br.com.linkstart.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.linkstart.data.vo.ProdutoVO;

@Component
public class ProdutoSendMessagem {

	@Value("${crud.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	private String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public ProdutoSendMessagem(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(ProdutoVO produtoVO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, produtoVO);
	}
	
}
