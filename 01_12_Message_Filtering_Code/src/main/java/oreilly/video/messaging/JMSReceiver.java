package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver implements MessageListener {

	public JMSReceiver() {
		try {
			Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_TRADE_FILTER.Q");
			//MessageConsumer receiver = session.createConsumer(queue); 
			MessageConsumer receiver = 
				session.createConsumer(queue, "State = 'open'"); 
			receiver.setMessageListener(this);
			System.out.println("Waiting for messages...");
			System.out.println("filter: " + receiver.getMessageSelector());
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public void onMessage(Message message) {
		try {
			TextMessage msg = (TextMessage)message;
			System.out.println(msg.getText());
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMSReceiver();
	    }}.start();
	}

}
