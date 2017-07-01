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
	
    public void onMessage(Message message) {
    	try {
			System.out.println(((TextMessage)message).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JMSReceiver() {
		try {
			Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_TRADE.Q");
			MessageConsumer receiver = session.createConsumer(queue);
			receiver.setMessageListener(this);
			System.out.println("Waiting for messages...");
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
