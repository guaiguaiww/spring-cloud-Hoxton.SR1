package com.hww.comtroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/25-17:31
 * @Description:
 */
@RestController
@EnableBinding(Sink.class) //让通道和队列绑在一起,指定为目的地
public class ReceiveMessageController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * @param message
     * @StreamListener: 监听队列
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者" + serverPort + "号,----->接受到的消息: " + message.getPayload());
        //手动提交数据
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }


    }

}
