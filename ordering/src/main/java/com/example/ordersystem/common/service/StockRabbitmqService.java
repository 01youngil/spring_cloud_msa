package com.example.ordersystem.common.service;

import com.example.ordersystem.common.config.RabbitmqConfig;
import com.example.ordersystem.common.dto.StockRabbitDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockRabbitmqService {
    private final RabbitTemplate template;


    public StockRabbitmqService(RabbitTemplate template) {
        this.template = template;
    }

    //    mq에 rdb동기화관련 메시지를 발행
    public void publish(StockRabbitDto dto){
        template.convertAndSend(RabbitmqConfig.STOCK_DECREASE_QUEUE, dto);
    }
////    mq에 저장된 메시지를 소비하여 rdb에 동기화
////    listener는 publish와는 독립적으로 동작하기 때문에, 비동기적으로 실행.
////    한트랜잭션이 완료된 이후에 그 다음 메시지 수신하므로, 동시성이슈발생X
//    @RabbitListener(queues = RabbitmqConfig.STOCK_DECREASE_QUEUE)
//    @Transactional
//    public void subscribe(Message message) throws JsonProcessingException {
////        {"productId":1, "productCount":3}
//        String messageBody = new String(message.getBody());
//        ObjectMapper objectMapper = new ObjectMapper();
//        StockRabbitDto dto = objectMapper.readValue(messageBody, StockRabbitDto.class);
//        Product product = productRepository.findById(dto.getProductId()).orElseThrow(()->new EntityNotFoundException("product is not found"));
//        product.updateStockQuantity(dto.getProductCount());
//    }
}
