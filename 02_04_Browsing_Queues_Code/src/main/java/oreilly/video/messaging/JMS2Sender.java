package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import com.sun.messaging.ConnectionFactory;

public class JMS2Sender {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			JMSProducer sender = jmsContext.createProducer();
			for (int i=1;i<6;i++) {
				sender.send(queue, "BUY AAPL " + i*1000 + " SHARES");
			}
			System.out.println("messages sent");
		}
	}
}






































