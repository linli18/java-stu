package com.linli.rabbitmq;

import com.linli.rabbitmq.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Stu03RabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
        //rabbitTemplate.send("exchanges.direct", "test", new Message("aaaa".getBytes(), new MessageProperties()));
        Map<String, Object> message = new HashMap<>();
        message.put("aaa", "ccc");
        message.put("list", Arrays.asList("aaa", 111, true));
        rabbitTemplate.convertAndSend("exchanges.direct", "test.aa", message);
    }

    @Test
    void testAmqpAdmin() {
//        amqpAdmin.declareExchange(new DirectExchange("test111", true, false));
//        amqpAdmin.declareQueue(new Queue("testqueue", true));
        amqpAdmin.declareBinding(new Binding("testqueue", Binding.DestinationType.QUEUE, "test111", "test.aaaaa", null));
    }

    @Test
    void sendObject() {
        rabbitTemplate.convertAndSend("exchanges.fanout", "", new Book("西游记", "吴承恩", 100));
    }

    @Test
    void sendObject1() {
        rabbitTemplate.convertAndSend("exchanges.topic", "test.test", new Book("红楼梦", "曹雪芹", 100));
    }

    @Test
    void receive(){
        Object message = rabbitTemplate.receiveAndConvert("test.aa");
        System.out.println(message.getClass());
        System.out.println(message);
    }

}
