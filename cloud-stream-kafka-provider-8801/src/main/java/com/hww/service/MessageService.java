package com.hww.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/25-16:28
 * @Description:  @EnableBinding(Source.class):定义消息的推送管道为源
 *
 * 消息发送给通道，通道发送给消费者，消费者是与通道绑定的。
 */

@EnableBinding(Source.class) //让通道和队列绑在一起,指定为源
@Service
@Slf4j
public class MessageService {

    /**
     * 消息发送管道
     */
    @Resource
    private Source source;

    public String send() {
        String serial = UUID.randomUUID().toString();
        source.output().send(MessageBuilder.withPayload(serial).build());
        log.info("**message-sent-successfully ***serial: " + serial);
        return serial;
    }
}
