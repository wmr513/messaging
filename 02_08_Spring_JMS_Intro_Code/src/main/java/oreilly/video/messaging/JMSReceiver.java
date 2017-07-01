package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSReceiver {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("app-context.xml");
			System.exit(0);
	}
}
