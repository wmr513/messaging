package oreilly.video.messaging;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

//public class TradeProcessor implements MessageListener {
//public class TradeProcessor implements SessionAwareMessageListener {
public class TradeProcessor {

	//public void onMessage(Message message, Session session) {
	//public void handleMessage(String body) {
	public void placeTrade(String trade) {
		try {
			//TextMessage msg = (TextMessage)message;
			//System.out.println("Message: " + msg.getText());
			//System.out.println(body);
			System.out.println("Processing Trade: " + trade);
			
			//MessageProducer sender = session.createProducer(msg.getJMSReplyTo());
			//TextMessage confMsg = session.createTextMessage("12345");
			//confMsg.setJMSCorrelationID(msg.getJMSMessageID());
			//sender.send(confMsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
