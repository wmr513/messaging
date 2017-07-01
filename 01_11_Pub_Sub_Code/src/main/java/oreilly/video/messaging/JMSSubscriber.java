package oreilly.video.messaging;


import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSubscriber implements MessageListener {
	
	public JMSSubscriber() {
		try {
			TopicConnection connection = 
				new ActiveMQConnectionFactory("tcp://localhost:61616?jms.clientID=host:2q")
			    .createTopicConnection();
			connection.start();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("EM_TRADE.T");
			
			//TopicSubscriber subscriber = session.createSubscriber(topic);
			TopicSubscriber subscriber = session.createDurableSubscriber(topic, "sub:2q");
			subscriber.setMessageListener(this);
			System.out.println("Waiting for messages...");			
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public void onMessage(Message message) {
		try {
			TextMessage msg = (TextMessage) message;
			System.out.println(msg.getText());
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMSSubscriber();
	    }}.start();
	}

}

