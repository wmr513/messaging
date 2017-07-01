package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

import com.sun.messaging.ConnectionFactory;

public class JMS2Receiver {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			Message msg = jmsContext.createConsumer(queue).receive();
			displayHeaders(msg);
			System.out.println(msg.getBody(String.class));
		}
	}
	
	public static void displayHeaders(Message msg) throws Exception {
		System.out.println("JMSDeliveryMode: " + msg.getJMSDeliveryMode());
		System.out.println("JMSExpiration:   " + msg.getJMSExpiration());
		System.out.println("JMSPriority:     " + msg.getJMSPriority());
	}

}
		
		













