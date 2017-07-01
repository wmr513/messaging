package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("TRADE.Q");
		
		MessageConsumer receiver = session.createConsumer(queue);
		TextMessage msg = (TextMessage)receiver.receive();
		System.out.println(msg.getText());
		connection.close();
	}

}
