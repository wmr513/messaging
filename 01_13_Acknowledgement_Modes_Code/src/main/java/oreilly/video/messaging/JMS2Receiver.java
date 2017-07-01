package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Session;

import com.sun.messaging.ConnectionFactory;

public class JMS2Receiver {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();		
		//try (JMSContext jmsContext = cf.createContext();) {
		try (JMSContext jmsContext = cf.createContext(JMSContext.CLIENT_ACKNOWLEDGE);) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			String payload = jmsContext.createConsumer(queue).receiveBody(String.class);
			System.out.println("Message Received, processing...");
			Thread.sleep(6000);
			System.out.println("Message processed: " + payload);
			System.out.println(payload);
			jmsContext.acknowledge();
		}
	}
}
		
		













