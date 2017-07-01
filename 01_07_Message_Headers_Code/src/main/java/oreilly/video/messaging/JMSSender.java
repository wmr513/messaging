package oreilly.video.messaging;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
    	connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("EM_TRADE.Q");
		MessageProducer sender = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("BUY AAPL 1000 SHARES");
		
		//msg.setStringProperty("Amount", "1111.aa");
		msg.setStringProperty("not", "1111.aa");
		
		//msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		//msg.setJMSExpiration(new Date().getTime() + 1234567);
		//sender.setTimeToLive(1234567);
		
		//msg.setJMSPriority(9);
		//sender.setPriority(9);
		
		displayHeaders(msg);
		sender.send(msg);
		System.out.println("Message Sent");
		displayHeaders(msg);
		connection.close();
	}
	
	public static void displayHeaders(Message msg) throws Exception {
		System.out.println("JMSDeliveryMode: " + msg.getJMSDeliveryMode());
		System.out.println("JMSExpiration:   " + msg.getJMSExpiration());
		System.out.println("JMSPriority:     " + msg.getJMSPriority());
	}	
}
