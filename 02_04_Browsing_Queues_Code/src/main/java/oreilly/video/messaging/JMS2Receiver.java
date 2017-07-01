package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

import com.sun.messaging.ConnectionFactory;

public class JMS2Receiver implements MessageListener {

	public JMS2Receiver() {
		try {
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			JMSContext jmsContext = cf.createContext();
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			jmsContext.createConsumer(queue).setMessageListener(this);
			System.out.println("waiting on messages");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onMessage(Message message) {
		try {			
			System.out.println(message.getBody(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMS2Receiver();
	    }}.start();
	}
}
		
		













