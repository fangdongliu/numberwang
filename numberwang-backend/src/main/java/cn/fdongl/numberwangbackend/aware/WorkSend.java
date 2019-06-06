package cn.fdongl.numberwangbackend.aware;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 张博
 */
@Component
public class WorkSend {

    @Autowired
    private Queue workQueue;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Long msg) {
        rabbitTemplate.convertAndSend(workQueue.getName(), msg);
    }
}