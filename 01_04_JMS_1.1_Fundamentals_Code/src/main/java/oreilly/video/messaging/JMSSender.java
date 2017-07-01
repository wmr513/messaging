package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
//		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
//		Connection connection = cf.createConnection();
//		connection.start();
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		Queue queue = session.createQueue("TRADE.Q");
		
		Context ctx = new InitialContext();
		Connection connection = ((ConnectionFactory) ctx.lookup("ConnectionFactory")).createConnection();
		connection.start();
		Queue queue = (Queue) ctx.lookup("TRADE.Q");
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		MessageProducer sender = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("BUY AAPL 1000 SHARES");
		msg.setStringProperty("TraderName", "Mark");
		sender.send(msg);
		System.out.println("Message sent");
		connection.close();
	}
}
