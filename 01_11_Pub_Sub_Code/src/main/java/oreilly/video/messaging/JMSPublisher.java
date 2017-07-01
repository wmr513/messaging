package oreilly.video.messaging;

import java.text.DecimalFormat;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSPublisher {

	public static void main(String[] args) {
		try {
			Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("EM_TRADE.T");
			MessageProducer publisher = session.createProducer(topic);

			DecimalFormat df = new DecimalFormat("##.00");
			String price = df.format(95.0 + Math.random());
			TextMessage msg = session.createTextMessage("AAPL " + price);
			
			publisher.send(msg);
			System.out.println("Message sent: AAPL " + price);
			connection.close();
			
		} catch (Exception up) {
			up.printStackTrace();
		}
	}
}


