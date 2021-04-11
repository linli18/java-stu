package com.linli.rabbitmq.listener;

import com.linli.rabbitmq.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookListener {
    @RabbitListener(queues = "test.bb")
    public void receive(Book book) {
        System.out.println(book.toString());
    }
}
