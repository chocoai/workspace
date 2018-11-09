package com.whty.activeMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * \* User: zjd
 * \* Date: 2018/10/19
 * \* Time: 16:49
 * \* Description:
 * \
 */
public class JMSQueueProducer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory
                        ("tcp://127.0.50.197:61616");
        Connection connection=null;
        try {

            connection=connectionFactory.createConnection();
            connection.start();

            Session session=connection.createSession
                    (Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination=session.createQueue("myQueue");
            //创建发送者
            MessageProducer producer=session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for(int i=0;i<10;i++) {
                //创建需要发送的消息
                TextMessage message = session.createTextMessage("Hello World:"+i);
                //Text   Map  Bytes  Stream  Object
                producer.send(message);
            }

//            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}