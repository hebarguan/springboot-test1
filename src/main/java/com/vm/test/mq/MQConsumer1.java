package com.vm.test.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


/**
 * @author huaihai.guan
 * @since 2021/7/21 20:34
 */
public class MQConsumer1 {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_01");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("shopTopic", "*");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            msgs.forEach(x -> {
                try {
                    System.out.printf("%s Receive New Messages: %s %s %n",
                            Thread.currentThread().getName(),
                            "consumer_02",
                            new String(x.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            return ConsumeOrderlyStatus.SUCCESS;
            }
        );

        consumer.start();
    }
}
