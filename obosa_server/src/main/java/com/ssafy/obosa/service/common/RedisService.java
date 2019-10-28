package com.ssafy.obosa.service.common;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class RedisService implements MessageListener
{
    public static List<String> messageList = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern)
    {
            messageList.add(message.toString());
            System.out.println("Message received : " + message.toString());
    }
}
