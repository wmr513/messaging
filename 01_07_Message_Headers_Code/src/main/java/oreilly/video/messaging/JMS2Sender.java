package oreilly.video.messaging;

import java.util.Date;

import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.sun.messaging.ConnectionFactory;

public class JMS2Sender {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");
			TextMessage msg = jmsContext.createTextMessage("BUY AAPL 1000 SHARES");
			//msg.setJMSDeliveryMode(1);
			//msg.setJMSExpiration(1234567);
			//msg.setJMSPriority(9);
			
			displayHeaders(msg);		
			jmsContext
			.createProducer()
			//.setDeliveryMode(1)
			//.setTimeToLive(1234567)
			//.setPriority(9)
			//.setProperty("not", 12345)
			.send(queue, msg);
			System.out.println("message sent");
			displayHeaders(msg);
		}
	}
	
	public static void displayHeaders(Message msg) throws Exception {
		System.out.println("JMSDeliveryMode: " + msg.getJMSDeliveryMode());
		System.out.println("JMSExpiration:   " + msg.getJMSExpiration());
		System.out.println("JMSPriority:     " + msg.getJMSPriority());
	}
	
}






































