package oreilly.video.messaging;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class RestSender {

	public static void main(String[] args) {
		
	    try {
	    	QueueConnection connection = 
	    		new ActiveMQConnectionFactory("tcp://localhost:61616").createQueueConnection();
	    	QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	    	Queue queue = session.createQueue("REST_IN.Q");
			QueueSender sender = session.createSender(queue);
			connection.start();

			String text = 					
				"<html><body>This message was send on " 
				+ new java.util.Date() + "</body></html>";

			TextMessage msg = session.createTextMessage(text);
			sender.send(msg);
			System.out.println("Message sent: " + text);

			connection.close();
	    	System.exit(0);
	    } catch (Exception up) {
	        up.printStackTrace();
	    }
	}
}
















