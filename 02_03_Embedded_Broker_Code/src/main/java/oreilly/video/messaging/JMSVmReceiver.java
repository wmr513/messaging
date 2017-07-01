package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSVmReceiver implements MessageListener {
	
	private int id;
	
	public JMSVmReceiver(int id) {
		try {
			this.id = id;
			Connection connection = new ActiveMQConnectionFactory("vm://embedded1").createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_EMBED_TRADE.Q");
			MessageConsumer receiver = session.createConsumer(queue);
			receiver.setMessageListener(this);
			System.out.println("Receiver (" + id + "): Waiting for messages...");
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public void onMessage(Message message) {
		try {
			TextMessage msg = (TextMessage)message;
			Thread.sleep(1000);
			System.out.println("Trade Received (" + id + "): " + msg.getText());
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

}
