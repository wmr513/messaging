package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
    	Queue queue = session.createQueue("EM_TRADE.Q");    			
		MessageConsumer receiver = session.createConsumer(queue);
		TextMessage msg1 = (TextMessage)receiver.receive();
		System.out.println(msg1.getText());
		Thread.sleep(6000); //wait 10s so we can view the console to see the message
		TextMessage msg2 = (TextMessage)receiver.receive();
		System.out.println(msg2.getText());
		Thread.sleep(6000);
		session.commit();
		//session.rollback();
		connection.close();
	}
}
