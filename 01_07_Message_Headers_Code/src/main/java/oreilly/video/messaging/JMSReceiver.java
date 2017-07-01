package oreilly.video.messaging;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {

	public static void main(String[] args) throws Exception {
		
		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	Queue queue = session.createQueue("EM_TRADE.Q");    			
		MessageConsumer receiver = session.createConsumer(queue);		
		TextMessage msg = (TextMessage)receiver.receive();
		displayHeaders(msg);
		System.out.println(msg.getText());
		connection.close();
	}

	public static void displayHeaders(Message msg) throws Exception {
		System.out.println("JMSDeliveryMode: " + msg.getJMSDeliveryMode());
		System.out.println("JMSExpiration:   " + msg.getJMSExpiration());
		System.out.println("JMSPriority:     " + msg.getJMSPriority());
		System.out.println("Amount:          " + msg.getStringProperty("Amount")); 
		System.out.println("not:          " + msg.getStringProperty("not")); 
		//System.out.println("Amount:          " + msg.getDoubleProperty("Amount")); 

		
	}

}
