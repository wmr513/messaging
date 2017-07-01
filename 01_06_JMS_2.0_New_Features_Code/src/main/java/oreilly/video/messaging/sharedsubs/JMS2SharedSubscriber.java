package oreilly.video.messaging.sharedsubs;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

import com.sun.messaging.ConnectionFactory;

public class JMS2SharedSubscriber implements MessageListener {

	public JMS2SharedSubscriber() {
		
		try {
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			JMSContext jmsContext = cf.createContext();
			Topic topic = jmsContext.createTopic("EM_JMS2_TRADE.T");
			
			//JMSConsumer jmsConsumer = jmsContext.createConsumer(topic);
			JMSConsumer jmsConsumer = jmsContext.createSharedConsumer(topic, "sub1q");
			
			jmsConsumer.setMessageListener(this);
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
	    		new JMS2SharedSubscriber();
	    }}.start();
	}
}
