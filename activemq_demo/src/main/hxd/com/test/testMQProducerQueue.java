package com.test;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;



public class testMQProducerQueue {


    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        //创建工厂，构造方法有三个参数，用户名，密码，连接地址
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory("tcp://39.99.151.163:61616");
        //通过工厂，创立连接对象

        Connection connection = connectionFactory.createConnection();
        //如果有特殊的配置，建议配置完毕后再启动连接
        connection.start();

        //创建会话对象
        /**
         * 创建会话，有两个参数，1.是否支持事务 2.如何确定消息处理
         *
        * */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");

        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("hello activemq");

        producer.send(textMessage);

        //关闭资源
        producer.close();
        session.close();
        connection.close();

    }

}
