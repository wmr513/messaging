package oreilly.video.messaging;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSBrowser {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
    	connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("EM_TRADE.Q");
		
		QueueBrowser browser = session.createBrowser(queue);
		Enumeration<?> e = browser.getEnumeration();

		int msgCount = 0;
		while (e.hasMoreElements()) {
			e.nextElement();
			msgCount++;
		}
		System.out.println("There are " + msgCount + " messages in the queue");

		browser.close();
        connection.close();
	}
}

