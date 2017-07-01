package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.sun.messaging.ConnectionFactory;

public class JMS2Receiver {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE_REQ.Q");
			Message msg = jmsContext.createConsumer(queue).receive();
			String payload = msg.getBody(String.class);
			System.out.println("processing trade: " + payload);
			Thread.sleep(4000);
			String confirmation = "EQ-12345";
			System.out.println("trade confirmation: " + confirmation);					

			jmsContext
			.createProducer()
			.setJMSCorrelationID(msg.getStringProperty("uuid"))
			.send(msg.getJMSReplyTo(), confirmation);
		}
	}
}
		
