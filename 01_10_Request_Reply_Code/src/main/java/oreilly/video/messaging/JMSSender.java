package oreilly.video.messaging;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueRequestor;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		QueueConnection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createQueueConnection();
    	connection.start();
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queueReq = session.createQueue("EM_TRADE_REQ.Q");		

		TextMessage msg = session.createTextMessage("BUY AAPL 1000 SHARES");				
		QueueRequestor requestor = new QueueRequestor(session, queueReq);
		TextMessage msgresp = (TextMessage) requestor.request(msg);
		
		System.out.println("confirmation: " + msgresp.getText());
		
//		Queue queueResp = session.createQueue("EM_TRADE_RESP.Q");		
//		TextMessage msg = session.createTextMessage("BUY AAPL 1000 SHARES");				
//		msg.setJMSReplyTo(queueResp);
//		MessageProducer sender = session.createProducer(queueReq);
//		sender.send(msg);
//		System.out.println("Message Sent");
//
//		String filter = "JMSCorrelationID = '" + msg.getJMSMessageID() + "'";
//		MessageConsumer receiver = session.createConsumer(queueResp, filter);
//		TextMessage msgIn = (TextMessage)receiver.receive();
//		System.out.println("confirmation = " + msgIn.getText());
		
		connection.close();
	}
}
