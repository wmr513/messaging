package oreilly.video.messaging;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class RestReceiver implements MessageListener {
	
	public RestReceiver() {
        try {
	    	QueueConnection connection = 
	    		new ActiveMQConnectionFactory("tcp://localhost:61616").createQueueConnection();
	    	QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	    	Queue queue = session.createQueue("REST_OUT.Q");
			connection.start();
			QueueReceiver receiver = session.createReceiver(queue);
			receiver.setMessageListener(this);			
	    	System.out.println("Waiting for messages...");
        } catch (Exception up) {
            up.printStackTrace();
        }
	}

	public void onMessage(Message message) {
        try {
        	TextMessage msg = (TextMessage)message;
        	System.out.println("Message: " + msg.getText());
	    } catch (Exception up) {
	    	up.printStackTrace();
	    }
	}

	public static void main(String[] args) {
	    new Thread() { 
	    	public void run() { 
	    		new RestReceiver();
	    }}.start();
	}

}











