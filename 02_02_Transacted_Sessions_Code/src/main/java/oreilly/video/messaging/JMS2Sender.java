package oreilly.video.messaging;

import javax.jms.JMSContext;
import javax.jms.Queue;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class JMS2Sender {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://localhost:7676");

		try (JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED);) { 
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			jmsContext.createProducer().send(queue, "BUY AAPL 1000 SHARES");
			System.out.println("message 1 sent");
			Thread.sleep(3000);
			jmsContext.createProducer().send(queue, "BUY IBM 2000 SHARES");
			System.out.println("message 2 sent");
			jmsContext.commit();
		}
	}
}






































