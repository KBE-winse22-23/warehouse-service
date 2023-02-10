package com.onlineshop.port.user.producer;

import com.onlineshop.core.domain.model.Product;
import com.onlineshop.port.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProducer {
    @Autowired
    private RabbitTemplate template;

    public String sendProductToProductService(Product product) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.PRODUCT_ROUTING_KEY,product);

        return "Message published: Product sent to Product Service";
    }

    public String updateProductQuantityInProductService(Product product) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.PRODUCT_UPDATE_ROUTING_KEY,product);

        return "Message published: Product Quantity updated in Product Service";
    }


//    public String sendProductAndUserInfoToCart(SendMessageToCartDto productAndUserInfo) {
//        template.convertAndSend(MQConfig.EXCHANGE,
//                MQConfig.CART_ROUTING_KEY,productAndUserInfo);
//
//        return "Product and User information has been sent to Cart-Service";
//    }
}
