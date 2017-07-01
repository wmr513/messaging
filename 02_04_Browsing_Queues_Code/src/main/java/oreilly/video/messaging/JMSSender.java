package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
    	connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("EM_TRADE.Q");
		MessageProducer sender = session.createProducer(queue);
		for (int i=1;i<6;i++) {
			TextMessage msg = session.createTextMessage("BUY AAPL " + i*1000 + " SHARES");
			sender.send(msg);
		}
		System.out.println("Messages Sent");
		connection.close();
	}
}
