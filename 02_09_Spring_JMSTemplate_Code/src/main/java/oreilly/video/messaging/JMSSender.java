package oreilly.video.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("app-context.xml");

		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");

		MessageCreator mc = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage("SELL AAPL 2000 SHARES");
				msg.setStringProperty("Trader", "Mark");
				return msg;
			}
		};
		
		MessagePostProcessor mpp = new MessagePostProcessor() {
			public Message postProcessMessage(Message msg) throws JMSException {
				msg.setStringProperty("Trader", "Mary");
				return msg;
			}
		};
		
		//jmsTemplate.convertAndSend("SELL AAPL 2000 SHARES");		
		//jmsTemplate.send(mc);		
		jmsTemplate.convertAndSend((Object)"SELL AAPL 2500 SHARES", mpp);		
		System.out.println("Message sent");
		System.exit(0);
	}
}
