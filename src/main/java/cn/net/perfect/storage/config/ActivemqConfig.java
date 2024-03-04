package cn.net.perfect.storage.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;


@Configuration
public class ActivemqConfig {

    @Autowired
    private Environment env;

    @Bean("jmsConnectionFactory")
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(env.getProperty("spring.activemq.broker-url"));
        connectionFactory.setUserName(env.getProperty("spring.activemq.user"));
        connectionFactory.setPassword(env.getProperty("spring.activemq.password"));
        return connectionFactory;
    }

    /**
     * 实现监听queue
     */
    @Bean("jmsQueueListenerContainerFactory")
    public JmsListenerContainerFactory<?> queueContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //开启接收topic类型的消息
        factory.setPubSubDomain(false);
        return factory;
    }

    /**
     * 队列名称
     */
    @Bean("dt50OnlineStatusChange")
    public Queue dt50OnlineStatusChangeQueue() {
        return new ActiveMQQueue("dt50OnlineStatusChange") ;
    }

}
