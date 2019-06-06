package cn.fdongl.numberwangmock.aware;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue workQueue() {
        return new Queue("work-queue");
    }

//    @Bean(name="work2")
//    public WorkReceiver workReceiver() {
//        return new WorkReceiver("Receiver0");
//    }
//
//    @Bean(name = "work1")
//    public WorkReceiver workReceiver1() {
//        return new WorkReceiver("Receiver1");
//    }


}