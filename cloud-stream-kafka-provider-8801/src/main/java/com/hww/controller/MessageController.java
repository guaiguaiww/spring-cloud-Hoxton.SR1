package com.hww.controller;

import com.hww.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author heweiwei@hztianque.com
 * @date 2020/3/25-16:31
 * @Description:
 */
@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageService.send();
    }

}
