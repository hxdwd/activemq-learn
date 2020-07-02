package com.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

//这个不是consumer去消费，而是设置一个监听器去
public class testMQConsumerQueue {
    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory("tcp://39.99.151.163:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);

        //注册监听器，
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });
        //阻塞当前代码
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }


}
