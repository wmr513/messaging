package oreilly.video.messaging;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

public class JMS2AsyncReceiver implements MessageListener {

	public JMS2AsyncReceiver() {

		try {
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			JMSContext jmsContext = cf.createContext();
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			jmsContext.createConsumer(queue).setMessageListener(this);
			System.out.println("Waiting on messages...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void onMessage(Message message) {
		try {
			if (message.isBodyAssignableTo(String.class))
				System.out.println("Message received: " + message.getBody(String.class));
			else 
				System.out.println("Invalid message type");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMS2AsyncReceiver();
	    }}.start();
	}
	
	
}
