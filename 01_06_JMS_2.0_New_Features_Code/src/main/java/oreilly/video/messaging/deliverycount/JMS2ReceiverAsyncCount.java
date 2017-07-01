package oreilly.video.messaging.deliverycount;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

import oreilly.video.messaging.sharedsubs.JMS2SharedSubscriber;

public class JMS2ReceiverAsyncCount implements MessageListener {

	JMSContext jmsContext = null;
	
	public JMS2ReceiverAsyncCount() {

		try {
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			jmsContext = cf.createContext(Session.SESSION_TRANSACTED);		
		    Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");		    
		    JMSConsumer consumer = jmsContext.createConsumer(queue);
			consumer.setMessageListener(this);
			System.out.println("Waiting on messages...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void onMessage(Message message) {
		try {
			System.out.println("Received: " + message.getBody(String.class));

			//enable code to show how to stop poison message
			//if (message.getIntProperty("JMSXDeliveryCount") > 2) {
			//	System.out.println("Cannot process message - sending to DLQ");
			//	jmsContext.commit();
			//	return;
			//}
			
			Thread.sleep(500);
			//simulated error...
			jmsContext.rollback();
			System.out.println("error processing message, retries = " + message.getIntProperty("JMSXDeliveryCount"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMS2ReceiverAsyncCount();
	    }}.start();
	}
}
