package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSVmSender {

	private MessageProducer sender;
	private Session session;
	
	public JMSVmSender() {
		try {
			Connection connection = new ActiveMQConnectionFactory("vm://embedded1").createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_EMBED_TRADE.Q");
			sender = session.createProducer(queue);
		} catch (Exception up) {
			up.printStackTrace();
		}
	}
	
	public void sendMessage(String trade) {
		try {
			TextMessage msg = session.createTextMessage(trade);
			sender.send(msg);
			System.out.println("Trade sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




