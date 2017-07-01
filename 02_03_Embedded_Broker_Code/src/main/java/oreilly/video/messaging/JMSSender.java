package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class JMSSender {

	public static void main(String[] args) throws Exception {

    	Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61888").createConnection();
    	connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("EM_EMBED_TRADE.Q");
		MessageProducer sender = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("BUY AAPL 1000 SHARES");
		sender.send(msg);
		System.out.println("Message Sent");
		connection.close();
	}
}




