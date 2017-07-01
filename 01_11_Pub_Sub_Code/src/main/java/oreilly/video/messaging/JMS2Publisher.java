package oreilly.video.messaging;

import java.text.DecimalFormat;

import javax.jms.JMSContext;
import javax.jms.Topic;

import com.sun.messaging.ConnectionFactory;

public class JMS2Publisher {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();		
		try (JMSContext jmsContext = cf.createContext();) {
			Topic topic = jmsContext.createTopic("EM_TRADE_JMS2.T");
			DecimalFormat df = new DecimalFormat("##.00");
			String price = df.format(95.0 + Math.random());
			jmsContext.createProducer().send(topic, price);
			System.out.println("Message sent: " + price);
		} 
	}
}

