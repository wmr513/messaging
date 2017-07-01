package oreilly.video.messaging;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {

	public static void main(String[] args) {

		try {
			QueueConnection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createQueueConnection();
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_TRADE.Q");
			QueueSender sender = session.createSender(queue);
			//sender.setDeliveryMode(DeliveryMode.PERSISTENT);
			sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			connection.start();

			long startTime = new Long(new java.util.Date().getTime());

			for (int i = 0; i < 1000; i++) {
				String body = "Message " + (i + 1);
				System.out.println("Sending JMS " + body);
				TextMessage msg = session.createTextMessage(body);
				msg.setLongProperty("startTime", startTime);
				msg.setLongProperty("count", (i + 1));
				sender.send(msg);
			}

			long endTime = new Long(new java.util.Date().getTime());
			System.out.println("\nElapsed Time: " + (endTime - startTime));

			connection.close();
			System.exit(0);
		} catch (Exception up) {
			up.printStackTrace();
		}
	}
}
