package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Queue;

import com.sun.messaging.ConnectionFactory;

public class JMS2Receiver {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			System.out.println("Waiting for messages");
			String payload = 
				jmsContext
				//.createConsumer(queue)
				.createConsumer(queue, "Validated > FALSE")
				.receiveBody(String.class); 
			System.out.println(payload);
		}
	}
}
		
		













