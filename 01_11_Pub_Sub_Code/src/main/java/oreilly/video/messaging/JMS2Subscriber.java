package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

import com.sun.messaging.ConnectionFactory;

public class JMS2Subscriber implements MessageListener {

	public JMS2Subscriber() {
		try {
			ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
			JMSContext jmsContext = cf.createContext();
			Topic topic = jmsContext.createTopic("EM_TRADE_JMS2.T");
			//jmsContext.createConsumer(topic).setMessageListener(this);
			//jmsContext.createSharedConsumer(topic, "sub:2w").setMessageListener(this);
			jmsContext.createSharedDurableConsumer(topic, "sub_d:2w").setMessageListener(this);
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
	    		new JMS2Subscriber();
	    }}.start();
	}
}
